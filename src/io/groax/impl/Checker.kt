package io.groax.impl

import io.groax.api.CC
import java.util.regex.Pattern

open class Checker {

    val pulled = HashMap<String, CC>()

    fun check(list: String, mode: Int) {
        val p = Pattern.compile("(([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))?(/([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))?)+)\\\\n[0-9]+/[0-9]+\\\\n([0-9]+){3}" + if (mode === 1) "\\\\n.*[İÖÜŞÇĞıöüşçğ]*." else "")
        val m = p.matcher(list)
        while (m.find()) {
            val out = m.group()
            val output = out.split("\\\\n".toRegex()).toTypedArray()
            val creditcard = output[0].replace("/", "")
            val tarih = output[1].split("/".toRegex()).toTypedArray()
            val ay = tarih[0]
            val yil = tarih[1]
            val cvv = output[2]
            val isimsoyad = output[3].replace("\"", "")
            pulled[isimsoyad] =  CC(creditcard, ay.toInt(), yil.toInt(), cvv.toInt())
        }
    }
}