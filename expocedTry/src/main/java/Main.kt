import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.opera.OperaOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*
import java.util.regex.Pattern


//jdbc:h2:mem:test
fun main(){

    val profile = OperaOptions()
    profile.addArguments("user-data-dir=C:/Users/Zoro/AppData/Roaming/Opera Software/Opera Stable")

    System.setProperty("webdriver.opera.driver", "C:/Users/Zoro/ideaFrameworks/operaDriver/operadriver.exe")
    val driver = OperaDriver(profile)
////    driver.get("https://www.google.ru/")
    driver.get("https://csgopolygon.gg/?login")
    Thread.sleep(2000)
    driver.findElement(By.xpath("//input[@class = 'btn_green_white_innerfade']")).click()

  createConnection()
    for (x in 1..50) {
            WebDriverWait(driver,100,2)
            .until (ExpectedConditions.textToBe(By.xpath("//div[@class='progress']/span[@class='progress_timer']")
                    ,"***ВРАЩЕНИЕ***"))
        playersBets(driver)
        println(x)
    }
//    for (x in 1..3){
//      WebDriverWait(driver,100,2)
//        .until (ExpectedConditions.textToBe(By.xpath("//div[@class='progress']/span[@class='progress_timer']")
//          ,"***ВРАЩЕНИЕ***"))
//    val redBetList = driver.findElements(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li"))
//    redBetList.forEach {
//        if (it.getAttribute("data-amount").toInt() >= 1000) {
//            println("${it.getAttribute("id").substringBefore('-')},${it.findElement(By.tagName("a")).getAttribute("href")} ,${it.getAttribute("data-amount")}")
//        }
//    }

}
fun searchElements (driver: WebDriver){

    for (cnt in 1..50){

        WebDriverWait(driver,46,2)
                .until (ExpectedConditions.textToBe(By.xpath("//div[@class='progress']/span[@class='progress_timer']")
                        ,"***ВРАЩЕНИЕ***"))

//        playersBets(driver)

        val red = driver.findElement(By.xpath("//*[@id='panel1-7-t']/div[1]/span[2]/strong")).text.toInt()
        val playersRed = driver.findElement(By.cssSelector("#panel1-7-t > div.round_total > span.players.totalCount")).text.toInt()

        val green = driver.findElement(By.xpath("//*[@id='panel0-0-t']/div[1]/span[2]/strong")).text.toInt()
        val playersGreen = driver.findElement(By.cssSelector("#panel0-0-t > div.round_total > span.players.totalCount")).text.toInt()

        val black = driver.findElement(By.xpath("//*[@id='panel8-14-t']/div[1]/span[2]/strong")).text.toInt()
        val playersBlack = driver.findElement(By.cssSelector("#panel8-14-t > div.round_total > span.players.totalCount")).text.toInt()

        WebDriverWait(driver,10,2)
                .until (ExpectedConditions.textMatches(By.xpath("//*[@id='select_roulette']/div[1]/div[1]/span")
                        , Pattern.compile("Выпало число (.*)")))

        val rollId = driver.findElement(By.xpath("//*[@id='select_roulette']//li[10]")).getAttribute("data-rollid").toInt()
        val output = driver.findElement(By.xpath("//*[@id='select_roulette']/div[1]/div[1]/span")).text
        val PGProfit = calculation(red,green,black,output)


        createConnection()
        playersBets(driver)
        insertPlayGround(roll_id = rollId,date = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
                ,bet_red = red,bet_green = green ,bet_black = black,players_red = playersRed
                ,players_green = playersGreen,players_black = playersBlack
                ,win_color = PGProfit.winColor,play_ground_profit = PGProfit.winCount)

        println("win color is : ${PGProfit.winColor} \n" +
                "PG profit is :${PGProfit.winCount}")

    }
}