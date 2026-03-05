package com.example.androidfinal.bd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.androidfinal.modelos.Coche

class DbRepositorioCoches(context: Context): RepositorioCoches {
    
    companion object {
        object CocheEntry : BaseColumns {
            const val TABLE_NAME = "coches"
            const val COL_NAME_MODELO = "modelo"
            const val COL_NAME_MARCA = "marca"
            const val COL_NAME_ANNO = "anno"
            const val COL_NAME_CARACTERISTICAS = "caracteristicas"
        }

        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${CocheEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${CocheEntry.COL_NAME_MODELO} TEXT," +
                    "${CocheEntry.COL_NAME_MARCA} TEXT," +
                    "${CocheEntry.COL_NAME_ANNO} INTEGER," +
                    "${CocheEntry.COL_NAME_CARACTERISTICAS} TEXT)"

        private const val SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS ${CocheEntry.TABLE_NAME}"

        class HelperBBDD(context: Context) :
            SQLiteOpenHelper(context, "coches.db", null, 1) {
            override fun onCreate(db: SQLiteDatabase?) {
                db?.execSQL(SQL_CREATE_TABLE)
            }

            override fun onUpgrade(
                db: SQLiteDatabase?,
                oldVersion: Int,
                newVersion: Int
            ) {
                db?.execSQL(SQL_DELETE_TABLE)
                onCreate(db)
            }
        }
    }

    private val helper = HelperBBDD(context)
    
    override fun adiciona(coche: Coche) {        
        
        val db = helper.writableDatabase

        val valores = ContentValues().apply {
            put(CocheEntry.COL_NAME_MODELO, coche.modelo)
            put(CocheEntry.COL_NAME_MARCA, coche.marca)
            put(CocheEntry.COL_NAME_ANNO, coche.anno)
            put(CocheEntry.COL_NAME_CARACTERISTICAS, coche.caracteristicas)
        }

        val nuevoID = db.insert(CocheEntry.TABLE_NAME, null, valores)
        Log.d("INSERT BBDD", "Insertado ${coche.modelo} en $nuevoID")
    }

    override fun actualiza(id: Int, coche: Coche) {
        val db = helper.writableDatabase

        val valores = ContentValues().apply {
            put(CocheEntry.COL_NAME_MODELO, coche.modelo)
            put(CocheEntry.COL_NAME_MARCA, coche.marca)
            put(CocheEntry.COL_NAME_ANNO, coche.anno)
            put(CocheEntry.COL_NAME_CARACTERISTICAS, coche.caracteristicas)
        }

        val where = "${BaseColumns._ID} = ?"
        val arrayArgs = arrayOf("${id + 1}")

        db.update(
            CocheEntry.TABLE_NAME,
            valores,
            where,
            arrayArgs
        )
    }

    override fun elimina(id: Int) {
        val db = helper.writableDatabase

        val where = "${BaseColumns._ID} = ?"
        val arrayArgs = arrayOf("${id + 1}")

        db.delete(
            CocheEntry.TABLE_NAME,
            where,
            arrayArgs
        )
    }

    override fun elemento(id: Int): Coche {
        val db = helper.readableDatabase

        val columnas = arrayOf(
            BaseColumns._ID,
            CocheEntry.COL_NAME_MODELO,
            CocheEntry.COL_NAME_MARCA,
            CocheEntry.COL_NAME_ANNO,
            CocheEntry.COL_NAME_CARACTERISTICAS
        )

        val condicion = "${BaseColumns._ID} = ?"
        val argumentos = arrayOf("${id + 1}")

        val cursor = db.query(
            CocheEntry.TABLE_NAME,
            columnas,
            condicion,
            argumentos,
            null,
            null,
            null
        )

        cursor.moveToNext()
        val r = Coche(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getInt(3),
            cursor.getString(4)
        )

        cursor.close()
        return r
    }

    override fun tamanno(): Int { // SELECT COUNT(*) FROM ...
        val db = helper.readableDatabase
        val sentencia = db.compileStatement(
            "SELECT COUNT(*) FROM ${CocheEntry.TABLE_NAME}")
        return sentencia.simpleQueryForLong().toInt()
    }
}