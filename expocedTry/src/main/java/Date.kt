import java.text.SimpleDateFormat
import java.util.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.regex.Pattern

data class TopPlayers(val playerId: Int = -1, val steamId : String = "Стима нет", val bet : Int = -1, val colorBet : String = "not found")

data class Outcome(val winColor : String, val winCount : Int)

fun Date.format(pattern: String = "dd-MM-yyyy HH:mm:ss") : String{
    val dateFormat = SimpleDateFormat (pattern, Locale("ru"))
    return  dateFormat.format(this)
}
fun calculation(red : Int = 0, green : Int = 0, black : Int = 0, output : String) : Outcome {
    val intResult = output.substring(output.length - 3).replace(Regex("[ !]"),"").toInt()
    return  when(intResult){
        in 1..7 -> return Outcome("red",(green + black) - red * 2)
        in 8..14 -> return Outcome("black",(red + green) - black * 2)
        else -> Outcome("green", (red + black) - green * 14)
    }
}

fun playersBets(driver : WebDriver){

    val topPlayers: MutableList<TopPlayers> = mutableListOf()
    val cheapGuys : MutableList<TopPlayers> = mutableListOf()

    val redBetList = driver.findElements(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li"))
    val greenBetList = driver.findElements(By.xpath("//*[@id='panel0-0-t']/div[2]/ul/li"))
    val blackBetList = driver.findElements(By.xpath("//*[@id='panel8-14-t']/div[2]/ul/li"))

    redBetList.forEach {
        if (it.getAttribute("data-amount").toInt() >= 1000){
            topPlayers.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
                    ,it.findElement(By.tagName("a")).getAttribute("href")
                    ,it.getAttribute("data-amount").toInt()
                    ,"red"))
        }
//        else {
//            cheapGuys.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
//                    ,it.findElement(By.tagName("a")).getAttribute("href")
//                    ,it.getAttribute("data-amount").toInt()
//                    ,"red"))
//        }
    }
    greenBetList.forEach {
        if (it.getAttribute("data-amount").toInt() >= 1000){
            topPlayers.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
                    ,it.findElement(By.tagName("a")).getAttribute("href")
                    ,it.getAttribute("data-amount").toInt()
                    ,"green"))
        }
//        else {
//            cheapGuys.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
//                    ,it.findElement(By.tagName("a")).getAttribute("href")
//                    ,it.getAttribute("data-amount").toInt()
//                    ,"green"))
//        }
    }
    blackBetList.forEach {
        if (it.getAttribute("data-amount").toInt() >= 1000){
            topPlayers.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
                    ,it.findElement(By.tagName("a")).getAttribute("href")
                    ,it.getAttribute("data-amount").toInt()
                    ,"black"))
        }
//        else {
//            cheapGuys.add(TopPlayers(it.getAttribute("id").substringBefore('-').toInt()
//                    ,it.findElement(By.tagName("a")).getAttribute("href")
//                    ,it.getAttribute("data-amount").toInt()
//                    ,"black"))
//        }
    }
/* Временное ожидание */
    WebDriverWait(driver,10,2)
            .until (ExpectedConditions.textMatches(By.xpath("//*[@id='select_roulette']/div[1]/div[1]/span")
                    , Pattern.compile("Выпало число (.*)")))
/*удалить*/    val output = driver.findElement(By.xpath("//*[@id='select_roulette']/div[1]/div[1]/span")).text
/*удалить*/    val rollId = driver.findElement(By.xpath("//*[@id='select_roulette']//li[10]")).getAttribute("data-rollid").toInt()

    val winColor = calculation(output = output).winColor
    topPlayers.sortBy { it.colorBet }

//    createConnection()

    for ((playerId,steamID,bet,colorBet) in topPlayers){
        insertPlayers(roll_id = rollId,date = Date().format()
                ,player_id = playerId,steam_id = steamID,bet = if (winColor == colorBet) {0 + bet} else {0 - bet}
                ,color_bet = colorBet, final_result = if (winColor == colorBet) {"win"} else {"lose"})
    }
//
//    for ((playerId,steamID,bet,colorBet) in cheapGuys){
//        insertCheapGuys(roll_id = rollId,date = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
//                ,player_id = playerId,steam_id = steamID,bet = if (winColor == colorBet) {0 + bet} else {0 - bet}
//                ,color_bet = colorBet, final_result = if (winColor == colorBet) {"win"} else {"lose"})
//    }

}


