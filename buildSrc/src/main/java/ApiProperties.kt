import java.util.Properties
import java.io.FileInputStream

object ApiProperties {
    fun load(apiPropertiesPath: String): Map<String, String> {
        val apiProperties = Properties().apply {
            val inputStream = FileInputStream(apiPropertiesPath)
            load(inputStream)
            inputStream.close()
        }
        val map = mutableMapOf<String, String>()
        apiProperties.stringPropertyNames().forEach {
            map[it] = apiProperties.getProperty(it)
        }
        return map
    }
}