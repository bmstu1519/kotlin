import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.sql.Date

object PlayGround : Table(){
    val roll_id = integer("roll_id").primaryKey()
    val date = varchar("date",25)
    val bet_red = integer("bet_red")
    val bet_green = integer("bet_green")
    val bet_black = integer("bet_black")
    val players_red = integer("players_red")
    val players_green = integer("players_green")
    val players_black = integer("players_black")
    val win_color = varchar("win_color", 5)
    val play_ground_profit = integer("play_ground_profit")
}

object Players : Table() {
    val roll_id = integer("roll_id")
    val date = varchar("date",25)
    val player_id = integer("player_id")
    val steam_id = varchar("steam_id", 100)
    val bet = integer("bet")
    val color_bet = varchar("color_bet", 5)
    val final_result = varchar("final_result", 4)
}

object CheapGuys : Table() {
    val roll_id = integer("roll_id")
    val date = varchar("date",25)
    val player_id = integer("player_id")
    val steam_id = varchar("steam_id", 100)
    val bet = integer("bet")
    val color_bet = varchar("color_bet", 5)
    val final_result = varchar("final_result", 4)
}

object test : Table(){
    val date = datetime("date")
}
fun createConnection(){
    Database.connect("jdbc:h2:tcp://localhost/C:/Users/Zoro/IdeaProjects/expocedTry/database", driver = "org.h2.Driver"
            ,user ="zorro",password = "qwerty123")
//    val dateTime = Date().format("M/d/y H:m:ss")
//println(dateTime)

}

fun insertPlayers (
        roll_id : Int,
        date : String,
        player_id : Int,
        steam_id : String,
        bet : Int,
        color_bet : String,
        final_result : String
){
    transaction {
        SchemaUtils.create(Players)

        Players.insert {
            it[this.roll_id] = roll_id
            it[this.date] = date
            it[this.player_id] = player_id
            it[this.steam_id] = steam_id
            it[this.bet] = bet
            it[this.color_bet] = color_bet
            it[this.final_result] = final_result
        }
    }

}
fun insertCheapGuys (
        roll_id : Int,
        date : String,
        player_id : Int,
        steam_id : String,
        bet : Int,
        color_bet : String,
        final_result : String
){
    transaction {
        SchemaUtils.create(CheapGuys)

        Players.insert {
            it[this.roll_id] = roll_id
            it[this.date] = date
            it[this.player_id] = player_id
            it[this.steam_id] = steam_id
            it[this.bet] = bet
            it[this.color_bet] = color_bet
            it[this.final_result] = final_result
        }
    }

}
fun insertPlayGround(
         roll_id : Int,
         date : String,
         bet_red : Int,
         bet_green : Int,
         bet_black : Int,
         players_red : Int,
         players_green : Int,
         players_black : Int,
         win_color : String,
         play_ground_profit : Int
){
    transaction {
//        addLogger(StdOutSqlLogger)
        SchemaUtils.create(PlayGround)

        PlayGround.insert {
            it[this.roll_id] = roll_id
            it[this.date] = date
            it[this.bet_red] = bet_red
            it[this.bet_green] = bet_green
            it[this.bet_black] = bet_black
            it[this.players_red] = players_red
            it[this.players_green] = players_green
            it[this.players_black] = players_black
            it[this.win_color] = win_color
            it[this.play_ground_profit] = play_ground_profit
        }
//        PlayGround.deleteWhere { PlayGround.roll_id eq 123 }
//        for (id in PlayGround.selectAll()){
//            println("$id")
//        }
        // SchemaUtils.drop(PlayGround)
    }
}

fun insertTest(
        date : DateTime
){
    test.insert {
        it[this.date] = date
    }
}