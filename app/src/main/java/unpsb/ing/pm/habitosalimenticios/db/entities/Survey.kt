package unpsb.ing.pm.habitosalimenticios.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "survey_table")
class Survey(
    @PrimaryKey @ColumnInfo(name = "food")
    val food: String

)