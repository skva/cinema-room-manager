package cinema

var purchasedTickets: Int = 0
var percentage: Double = 0.0
var formatPercentage: String = "0.0"
var currentIncome: Int = 0
var totalIncome: Int = 0

fun main() {
    val rows: Int = readRows()
    val seats: Int = readSeats()

    calculateTotalIncome(rows, seats)

    val hall = MutableList(rows) { MutableList(seats) { "S" } }

    while (true) {
        println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")
        when (readln().trim()) {
            "1" -> printHall(hall)
            "2" -> buy(hall)
            "3" -> showStatistics()
            "0" -> return
            else -> println("Wrong. Please input 1, 2, 3 or 0.")
        }
    }
}

fun readRows(): Int {
    println("Enter the number of rows:")
    return readln().toInt()
}

fun readSeats(): Int {
    println("Enter the number of seats in each row:")
    return readln().toInt()
}

fun printHall(hall: MutableList<MutableList<String>>) {
    println()
    println("Cinema:")
    print("  ")
    for (i in 1..hall[0].size) print("$i ")
    for (i in hall.indices) print("\n${i + 1} ${hall[i].joinToString(" ")}")
    println()
}

fun buy(hall: MutableList<MutableList<String>>) {
    var row: Int
    var seat: Int

    while (true) {
        try {
            row = enterRow()
            seat = enterSeat()
            if (hall[row - 1][seat - 1] != "B") {
                calculate(hall.size, hall[0].size, row)
                hall[row - 1][seat - 1] = "B"
                purchasedTickets++
                percentage = kotlin.math.round((purchasedTickets.toDouble() / (hall.size * hall[0].size).toDouble() * 100) * 100.00) / 100.00
                break
            } else {
                println("\nThat ticket has already been purchased!")
            }
        } catch (e: IndexOutOfBoundsException) {
            println("\nWrong input!")
        }
    }
}

fun enterRow(): Int {
    println("\nEnter a row number:")
    return readln().toInt()
}

fun enterSeat(): Int {
    println("Enter a seat number in that row:")
    return readln().toInt()
}

fun calculate(rows: Int, seats: Int, row: Int) {
    print("\nTicket price: $")
    if (rows * seats <= 60) {
        println(10)
        currentIncome += 10
    }
    if (rows * seats > 60) {
        if ((rows / 2) >= row) {
            println(10)
            currentIncome += 10
        } else {
            println(8)
            currentIncome += 8
        }
    }
}

fun calculateTotalIncome(rows: Int, seats: Int) {
    if (rows * seats <= 60) {
        totalIncome = rows * seats * 10
    } else {
        if (rows / 2 == 0) {
            totalIncome = (rows / 2 * seats * 10) + (rows / 2 * seats * 8)
        } else {
            totalIncome = (rows / 2 * seats * 10) + (rows / 2 * seats * 8) + seats * 8
        }
    }
}

fun showStatistics() {
    formatPercentage = "%.2f".format(percentage)
    println("\nNumber of purchased tickets: $purchasedTickets")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}
