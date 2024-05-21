package unpsb.ing.pm.habitosalimenticios.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Survey")
class Survey(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "food")
    val food: String,
    @ColumnInfo(name = "portion")
    val portion: String,
    @ColumnInfo(name = "frequency")
    val frequency: String
)