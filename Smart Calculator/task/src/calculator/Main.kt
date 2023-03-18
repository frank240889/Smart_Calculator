package calculator

import java.math.BigInteger
import java.util.*
import kotlin.math.pow
import kotlin.system.exitProcess


val REG_VALID_IDENTIFIERS = "[a-zA-Z]+".toRegex()
val REG_VARIABLE_ASSIGN = """.+(\s)*(=)(\s)*?(\s)*.+""".toRegex()
val REG_ONE_VARIABLE = """([a-zA-Z]+)(\w*|\d*)?""".toRegex()
val REG_ONE_NUMBER = """-?\d+""".toRegex()
val REG_INVALID_OPERANDS = """(\*{2,}|\/{2,})""".toRegex()
val REG_CONSECUTIVE_OPERANDS_ADDITION_SUBTRACTION = """(\++|\-+)+""".toRegex()
const val DIGITS_OF_MAX_LONG = Long.MAX_VALUE.toString()
val SUM_MAX_LONG_DIGITS = DIGITS_OF_MAX_LONG.sumOf { it.toString().toInt() }

interface Logger {
    fun log(message: Any?)
}

class LoggerImpl: Logger {
    override fun log(message: Any?) {
        println(message)
    }
}

class Calculator(
    private val logger: Logger
): Logger by logger {
    private val identifiers = mutableMapOf<String, String>()

    fun handleVariable(value: String) {
        getVarValue(value)?.let {
            log(it)
        } ?: log(AssignmentError.UNKNOWN_VARIABLE.message)
    }

    fun handleValue(value: String) {
        log(value)
    }

    fun handleCommand(input: String) {
        when (input.substring(1)) {
            "help" -> {
                log("The program calculates the sum of numbers")
            }
            "exit" -> {
                log("Bye!")
                exitProcess(0)
            }
            else -> {
                log("Unknown command")
            }
        }
    }

    fun handleAssignment(input: String) {
        val res = validateAssignment(input)
        if (res.first) {
            saveVariable(input)
        } else {
            log(res.second.message)
        }
    }

    fun handleOperation(input: String) {
        val parenthesisAreCorrect = parenthesisAreCorrect(input)
        if (!parenthesisAreCorrect) {
            log(AssignmentError.EXPRESSION_INVALID.message)
            return
        }

        if (hasInvalidOperators(input)) {
            log(AssignmentError.EXPRESSION_INVALID.message)
            return
        }

        val postfixNotation = parse(input)
        val result = calculateResult(postfixNotation)
        log(result)
    }

    private fun parenthesisAreCorrect(input: String): Boolean {
        val countOpenParenthesis = input.count { it == '(' }
        val countCloseParenthesis = input.count { it == ')'}
        return countOpenParenthesis == countCloseParenthesis
    }

    private fun hasInvalidOperators(input: String): Boolean {
        return REG_INVALID_OPERANDS.containsMatchIn(input)
    }

    private fun calculateResult(tokens: List<String>): String {
        val buffer = Stack<String>()
        val hasBigNumber = hasBigNumber(tokens)
        for (element in tokens) {
            if (isNumber(element) || isVariable(element)) {
                buffer.push(element)
            } else {
                val operand2 = if (isNumber(buffer.peek()))
                    toIntOrBigInteger(buffer.pop())
                else
                    toIntOrBigInteger(getVarValue(buffer.pop())!!)
                val operand1 = if (isNumber(buffer.peek()))
                    toIntOrBigInteger(buffer.pop())
                else
                    toIntOrBigInteger(getVarValue(buffer.pop())!!)
                val operator = getOperator(element)
                val operation = getOperation(operator)
                val result = operation(operand1, operand2, hasBigNumber)
                buffer.push(result.toString())
            }
        }

        return buffer.peek()
    }

    private fun hasBigNumber(tokens: List<String>): Boolean {
        return tokens.any { isDigit(it) && isBigInteger(it) }
    }

    private fun isBigInteger(input: String): Boolean {
        return input.length <= DIGITS_OF_MAX_LONG.length &&
                input.sumOf { it.toString().toInt() } < SUM_MAX_LONG_DIGITS
    }

    private fun toIntOrBigInteger(input: String): Number {
        val output = try {
            input.toInt()
        } catch (e: NumberFormatException) {
            try {
                input.toLong()
            } catch (e: NumberFormatException) {
                input.toBigInteger()
            }
        }
        return output
    }

    private fun getOperator(operator: String): Operator {
        return Operator.values().find { it.symbol == operator }!!
    }

    private fun getOperation(
        operator: Operator
    ): ((Number, Number, Boolean) -> Number) {
        return when (operator) {
            Operator.ADDITION -> {
                fun (op1: Number, op2: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().add(op2.toBigInteger())
                    } else {
                        op1.toInt() + op2.toInt()
                    }
                }
            }
            Operator.SUBTRACTION -> {
                fun (op1: Number, op2: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().subtract(op2.toBigInteger())
                    } else {
                        op1.toInt() - op2.toInt()
                    }
                }
            }
            Operator.MULTIPLICATION -> {
                fun (op1: Number, op2: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().multiply(op2.toBigInteger())
                    } else {
                        op1.toInt() * op2.toInt()
                    }
                }
            }
            Operator.DIVISION -> {
                fun (op1: Number, op2: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().divide(op2.toBigInteger())
                    } else {
                        op1.toInt() / op2.toInt()
                    }
                }
            }
            Operator.POWER -> {
                fun (op1: Number, power: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().pow(power.toInt())
                    } else {
                        op1.toDouble().pow(power.toInt()).toInt()
                    }
                }
            }
            Operator.MODULUS -> {
                fun (op1: Number, op2: Number, bigIntOp: Boolean): Number {
                    return if (bigIntOp) {
                        op1.toBigInteger().mod(op2.toBigInteger())
                    } else {
                        op1.toInt().mod(op2.toInt())
                    }
                }
            }
            Operator.INVALID_OPERATOR -> {
                throw IllegalArgumentException()
            }
        }
    }

    private fun validateAssignment(input: String): Pair<Boolean, AssignmentError> {
        val validAssignment = isValidAssignment(input)

        if (!validAssignment.first)
            return validAssignment

        val inputs = input
            .replace(" ","")
            .split("=")

        val isValidIdentifier = isValidIdentifier(inputs[0])

        if (!isValidIdentifier.first) {
            return isValidIdentifier
        }

        val isValidValue = isValidValue(inputs[1])
        if (!isValidValue.first) {
            return isValidValue
        }

        return Pair(true, AssignmentError.NONE)
    }

    private fun isValidIdentifier(input: String): Pair<Boolean, AssignmentError> {
        val variableNameIsValid = REG_VALID_IDENTIFIERS.matches(input)
        if (!variableNameIsValid)
            return Pair(false, AssignmentError.INVALID_IDENTIFIER)

        return Pair(true, AssignmentError.NONE)
    }

    private fun isValidAssignment(input: String): Pair<Boolean, AssignmentError> {
        val inputs = input
            .replace(" ","")
            .split("=")
        val validAssignment = inputs.size <= 2
        if (!validAssignment)
            return Pair(false, AssignmentError.INVALID_ASSIGNMENT)


        return Pair(true, AssignmentError.NONE)
    }

    private fun isValidValue(input: String): Pair<Boolean, AssignmentError> {
        val isNumber = isNumber(input)
        if (isNumber) {
            return Pair(true, AssignmentError.NONE)
        }

        val valueNameIsValid = REG_ONE_NUMBER.matches(input) || REG_ONE_VARIABLE.matches(input)
        if (!valueNameIsValid) {
            return Pair(false, AssignmentError.INVALID_ASSIGNMENT)
        }
        if (!identifiers.containsKey(input))
            return Pair(false, AssignmentError.UNKNOWN_VARIABLE)

        return Pair(true, AssignmentError.NONE)
    }

    private fun saveVariable(input: String) {
        val noSpacesInput = input
            .replace(" ","")
            .split("=")
        identifiers[noSpacesInput[0]] = noSpacesInput[1]
    }

    private fun getVarValue(key: String): String? {
        return if (key in identifiers.keys) {
            val value = identifiers[key]!!
            val isDigit = isDigit(value)
            if (isDigit) {
                value
            } else {
                getVarValue(value)
            }
        } else {
            null
        }
    }

    private fun isNumber(input: String?): Boolean {
        return input?.let {
            isDigit(it)
        } ?: false
    }

    private fun isDigit(element: String): Boolean {
        return REG_ONE_NUMBER.matches(element)
    }

    private fun isVariable(element: String): Boolean {
        return REG_ONE_VARIABLE.matches(element)
    }

    private fun parse(input: String): MutableList<String> {
        val parsedInput = addSpacesToParenthesis(input)
        val tokens = getTokens(parsedInput, " ")
        val output: MutableList<String> = LinkedList()
        val stack = Stack<String>()
        val ops = HashMap<String, Operator>().apply {
            for (operator in Operator.values()) {
                put(operator.symbol, operator)
            }
        }

        // For all the input tokens [S1] read the next token [S2]
        for (token in tokens) {
            if (ops.containsKey(token) || REG_CONSECUTIVE_OPERANDS_ADDITION_SUBTRACTION.matches(token)) {
                val operator = parseOperator(token)
                val tokenOp = operator.symbol
                // Token is an operator [S3]
                while (!stack.isEmpty() && ops.containsKey(stack.peek())) {
                    // While there is an operator (y) at the top of the operators stack and
                    // either (x) is left-associative and its precedence is less or equal to
                    // that of (y), or (x) is right-associative and its precedence
                    // is less than (y)
                    //
                    // [S4]:
                    val currentOperator: Operator = ops[tokenOp]!! // Current operator
                    val currentStackOperator: Operator = ops[stack.peek()]!! // Top operator from the stack
                    if (currentOperator.associativity === Associativity.LEFT &&
                        currentOperator.comparePrecedence(currentStackOperator) <= 0 ||
                        currentOperator.associativity === Associativity.RIGHT &&
                        currentOperator.comparePrecedence(
                            currentStackOperator
                        ) < 0
                    ) {
                        // Pop (y) from the stack S[5]
                        // Add (y) output buffer S[6]
                        output.add(stack.pop())
                        continue
                    }
                    break
                }
                // Push the new operator on the stack S[7]
                stack.push(tokenOp)
            } else if ("(" == token) {
                // Else If token is left parenthesis, then push it on the stack S[8]
                stack.push(token)
            } else if (")" == token) {
                // Else If the token is right parenthesis S[9]
                while (!stack.isEmpty() && stack.peek() != "(") {
                    // Until the top token (from the stack) is left parenthesis, pop from
                    // the stack to the output buffer
                    // S[10]
                    output.add(stack.pop())
                }
                // Also pop the left parenthesis but don't include it in the output
                // buffer S[11]
                stack.pop()
            } else {
                // Else add token to output buffer S[12]
                output.add(token)
            }
        }

        while (!stack.isEmpty()) {
            // While there are still operator tokens in the stack, pop them to output S[13]
            output.add(stack.pop())
        }

        return output
    }

    private fun addSpacesToParenthesis(input: String): String {
        return input.replace("(", " ( ").replace(")", " ) ")
    }

    private fun getTokens(input: String, splitBy: String): List<String> {
        return input.split(splitBy).filter { it != "" }
    }

    private fun parseOperator(input: String): Operator {
        return when {
            input.length == 1 -> {
                getOperator(input)
            }
            input.all { it == '-' } -> {
                if (input.length.mod(2) == 0) {
                    Operator.ADDITION
                } else {
                    Operator.SUBTRACTION
                }
            }
            input.all { it == '+' } -> {
                Operator.ADDITION
            }
            else -> reduceOperators(input)
        }
    }

    private fun reduceOperators(input: String): Operator {
        val res = input.reduce { acc, element ->
            when {
                (acc == '-' && element == '+') ||
                        (acc == '+' && element == '-')-> {
                    '-'
                }
                else -> {
                    '+'
                }
            }
        }
        return getOperator(res.toString())
    }
}

object InputValidator {
    fun evaluateInput(input: String): Input {
        return when {
            input.isEmpty() -> Input.EMPTY_INPUT
            input.startsWith("/") -> Input.COMMAND
            REG_ONE_VARIABLE.matches(input) -> Input.VAR
            REG_ONE_NUMBER.matches(input) -> Input.NUM
            REG_VARIABLE_ASSIGN.matches(input) -> Input.VARIABLE_ASSIGNATION
            else -> Input.OPERATION
        }
    }
}

enum class Input {
    EMPTY_INPUT,
    COMMAND,
    VAR,
    NUM,
    VARIABLE_ASSIGNATION,
    OPERATION
}

enum class AssignmentError(val message: String) {
    INVALID_IDENTIFIER("Invalid identifier"),
    UNKNOWN_VARIABLE("Unknown variable"),
    INVALID_ASSIGNMENT("Invalid assignment"),
    NONE(""),
    EXPRESSION_INVALID("Invalid expression")
}

enum class Associativity {
    LEFT,
    RIGHT
}

enum class Operator(val symbol: String, val associativity: Associativity, val precedence: Int) : Comparable<Operator> {
    ADDITION("+", Associativity.LEFT, 0),
    SUBTRACTION("-", Associativity.LEFT, 0),
    DIVISION("/", Associativity.LEFT, 5),
    MULTIPLICATION("*", Associativity.LEFT, 5),
    MODULUS("%", Associativity.LEFT, 5),
    POWER("^", Associativity.RIGHT, 10),
    INVALID_OPERATOR("", Associativity.LEFT, 0);

    fun comparePrecedence(operator: Operator): Int {
        return precedence - operator.precedence
    }
}

fun Number.toBigInteger(): BigInteger {
    return when (this) {
        is Int -> BigInteger.valueOf(toLong())
        is Long -> BigInteger.valueOf(this)
        is BigInteger -> this
        else -> BigInteger.valueOf(toLong())
    }
}

fun main() {
    val calculator = Calculator(LoggerImpl())
    while(true) {
        val input = readln().trim()
        when (val res = InputValidator.evaluateInput(input)) {
            Input.EMPTY_INPUT -> continue
            Input.COMMAND -> calculator.handleCommand(input)
            Input.VAR -> calculator.handleVariable(input)
            Input.NUM -> calculator.handleValue(input)
            Input.VARIABLE_ASSIGNATION -> calculator.handleAssignment(input)
            Input.OPERATION -> calculator.handleOperation(input)
        }
    }
}