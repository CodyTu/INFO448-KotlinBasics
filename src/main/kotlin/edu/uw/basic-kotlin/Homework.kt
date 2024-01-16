package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when (arg) {
        "Hello" -> return("world")
        is String -> return("Say what?")
        0 -> return("zero")
        1 -> return("one")
        in 2..10 -> return("low number")
        is Int -> return("a number")
        else -> return("I don't understand")
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    val acceptedCurrencies = listOf("USD", "EUR", "CAN", "GBP")
    init {
        require(amount > 0 && currency in acceptedCurrencies)
    }
        
    val toString: String = "[${amount}, ${currency}]"

    public fun convert(conversionCurrency: String): Money {
            
        fun convertUSD(amount: Int, currency: String): Int {
            when (currency) {
                "EUR" -> return (2 * amount) / 3
                "CAN" -> return (4 * amount) / 5
                "GBP" -> return 2 * amount
                else -> return amount
            }
        }
            
        when (conversionCurrency) {
            "EUR" -> return Money((convertUSD(this.amount, this.currency)*3)/2, conversionCurrency)
            "GBP" -> return Money(convertUSD(this.amount, this.currency)/2, conversionCurrency)
            "CAN" -> return Money((convertUSD(this.amount, this.currency)*5)/4, conversionCurrency)
            else -> return Money(convertUSD(this.amount, this.currency), conversionCurrency)
        }
    }

    operator fun plus(other: Money): Money = Money(this.amount + other.convert(this.currency).amount, this.currency)
}
