package io.groax.main

import io.groax.impl.Checker
import java.io.*
import java.nio.file.*
import java.util.*


object Runnable {
    @JvmStatic
    fun main(args: Array<String>) {
        //Example for Mode 1: 4111/1111/1111/1111\n12/2010\n031\nEmre Korkmaz
        //Example for Mode 2: 4111/1111/1111/1111\n12/2010\n031

        print("Ayıklancak dosyayı girin: ")
        val checker = Checker()
        val scanner = Scanner(System.`in`)
        val current = scanner.nextLine()
        println()
        println(" 1: İsimle beraber")
        println(" 2 = Sadece kart bilgileri")
        print("Modu seç: ")
        val currentMode = scanner.nextLine()
        val currentPath = /*Paths.get("").toAbsolutePath().toString()*/ "C:\\Users\\PC\\Documents\\CraftRiseAccountStealer" + "\\"
        val inputFile = File(currentPath + "\\" + current)
        val outputFile = File(currentPath + "\\" + current.replace("\\.[a-zA-Z]+".toRegex(), "") + "-out.txt")
        if (outputFile.exists()) {
            outputFile.delete()
        }
        outputFile.createNewFile()

        checker.check(String(Files.readAllBytes(inputFile.toPath())), currentMode.toInt())

        val fileWriter = FileWriter(outputFile)
        checker.pulled.forEach { (_, cc) ->
            val format: String = cc.kartnumarasi + "|" + cc.ay + "|" + cc.yil.toString().substring(2, 4) + "|" + cc.guvenlikkodu
            fileWriter.write("$format\n")
        }
        fileWriter.close()
    }
}