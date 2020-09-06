
//for (i in 1..3) {
///* Временное ожидание */
//    WebDriverWait(driver,146,2)
//            .until (ExpectedConditions.textToBe(By.xpath("//div[@class='progress']/span[@class='progress_timer']")
//                    ,"***ВРАЩЕНИЕ***"))
//
//    try {
//        driver.findElement(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li[$i]"))
//        topPlayers.add(TopPlayers(
//                driver.findElement(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li[$i]")).getAttribute("id")
//                , driver.findElement(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li[$i]/a")).getAttribute("href")
//                , driver.findElement(By.xpath("//*[@id='panel1-7-t']/div[2]/ul/li[$i]")).getAttribute("data-amount").toInt()
//                , "red"))
//    } catch (e: org.openqa.selenium.NoSuchElementException) {
////            topPlayers.add(TopPlayers())
//        println("In RED field, player by number $i not found")
//    }
//    try {
//        driver.findElement(By.xpath("//*[@id='panel0-0-t']/div[2]/ul/li[$i]"))
//        topPlayers.add(TopPlayers(
//                driver.findElement(By.xpath("//*[@id='panel0-0-t']/div[2]/ul/li[$i]")).getAttribute("id")
//                , driver.findElement(By.xpath("//*[@id='panel0-0-t']/div[2]/ul/li[$i]/a")).getAttribute("href")
//                , driver.findElement(By.xpath("//*[@id='panel0-0-t']/div[2]/ul/li[$i]")).getAttribute("data-amount").toInt()
//                , "green"))
//    } catch (e: org.openqa.selenium.NoSuchElementException) {
////            topPlayers.add(TopPlayers())
//        println("In GREEN field, player by number $i not found")
//    }
//    try {
//        driver.findElement(By.xpath("//*[@id='panel8-14-t']/div[2]/ul/li[$i]"))
//        topPlayers.add(TopPlayers(
//                driver.findElement(By.xpath("//*[@id='panel8-14-t']/div[2]/ul/li[$i]")).getAttribute("id")
//                , driver.findElement(By.xpath("//*[@id='panel8-14-t']/div[2]/ul/li[$i]/a")).getAttribute("href")
//                , driver.findElement(By.xpath("//*[@id='panel8-14-t']/div[2]/ul/li[$i]")).getAttribute("data-amount").toInt()
//                , "black"))
//    } catch (e: org.openqa.selenium.NoSuchElementException) {
////            topPlayers.add(TopPlayers())
//        println("In BLACK field, player by number $i not found")
//    }
//}