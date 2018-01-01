package net.frogbots.relicrecoveryscorecalculator.backend.export.csv;

import android.annotation.SuppressLint;
import android.content.Context;

import com.opencsv.CSVWriter;
import net.frogbots.relicrecoveryscorecalculator.backend.Scores;
import net.frogbots.relicrecoveryscorecalculator.backend.Utils;
import net.frogbots.relicrecoveryscorecalculator.backend.export.ExportBundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

class CsvCommon
{
    private static final String COLUMN_TIME                   = "Time";
    private static final String COLUMN_MATCH                  = "Match";
    private static final String COLUMN_TEAM                   = "Team";
    private static final String COLUMN_COMMENT                = "Comment";
    private static final String COLUMN_JEWEL                  = "Jewel";
    private static final String COLUMN_PRELOADED_GLPYH        = "Pre-loaded glyph";
    private static final String COLUMN_AUTO_GLPYHS_SCORED     = "[Auto] glyphs scored";
    private static final String COLUMN_AUTO_PARKING           = "Autonomous parking";
    private static final String COLUMN_TELE_GLYPHS_SCORED     = "[Tele-Op] glyphs scored";
    private static final String COLUMN_CRYPT_ROWS_COMPLETE    = "Cryptobox rows complete";
    private static final String COLUMN_CRYPT_COLUMNS_COMPLETE = "Cryptobox columns complete";
    private static final String COLUMN_CIPHER_COMPLETE        = "Cipher completed";
    private static final String COLUMN_RELIC_POSITION         = "Relic position";
    private static final String COLUMN_RELIC_UPRIGHT          = "Relic upright";
    private static final String COLUMN_ROBOT_BALANCED         = "Robot balanced";
    private static final String COLUMN_MINOR_PENALTIES        = "Minor penalties";
    private static final String COLUMN_MAJOR_PENALTIES        = "Major penalties";
    private static final String COLUMN_TOTAL_SCORE            = "TOTAL SCORE";

    static final String[] columns = {
            COLUMN_TIME,
            COLUMN_MATCH,
            COLUMN_TEAM,
            COLUMN_COMMENT,
            COLUMN_JEWEL,
            COLUMN_PRELOADED_GLPYH,
            COLUMN_AUTO_GLPYHS_SCORED,
            COLUMN_AUTO_PARKING,
            COLUMN_TELE_GLYPHS_SCORED,
            COLUMN_CRYPT_ROWS_COMPLETE,
            COLUMN_CRYPT_COLUMNS_COMPLETE,
            COLUMN_CIPHER_COMPLETE,
            COLUMN_RELIC_POSITION,
            COLUMN_RELIC_UPRIGHT,
            COLUMN_ROBOT_BALANCED,
            COLUMN_MINOR_PENALTIES,
            COLUMN_MAJOR_PENALTIES,
            COLUMN_TOTAL_SCORE
    };

    static int findIndexInColumnArray(String item)
    {
        for(int i = 0; i < columns.length; i ++)
        {
            if(columns[i].equals(item))
            {
                return i;
            }
        }
        return -1;
    }

    static void saveToCSV (Context context, File file, String[][] array) throws IOException
    {
        CSVWriter writer = new CSVWriter(new FileWriter(file));
        writer.writeAll(Arrays.asList(array));
        writer.close();
    }

    static void writeScoresToRow (String[][] array, ExportBundle bundle, int rowNumber)
    {
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("EEE d MMM yyyy HH:mm");

        array[rowNumber][findIndexInColumnArray(COLUMN_TIME)]                    = (df.format(Calendar.getInstance().getTime()));
        array[rowNumber][findIndexInColumnArray(COLUMN_MATCH)]                   = bundle.match;
        array[rowNumber][findIndexInColumnArray(COLUMN_TEAM)]                    = bundle.team;
        array[rowNumber][findIndexInColumnArray(COLUMN_COMMENT)]                 = bundle.comment;
        array[rowNumber][findIndexInColumnArray(COLUMN_JEWEL)]                   = Utils.jewelForExport(Scores.getAutonomousJewelLevel());
        array[rowNumber][findIndexInColumnArray(COLUMN_PRELOADED_GLPYH)]         = (Utils.glyphForExport(Scores.getAutonomousPreloadedGlyphLevel()));
        array[rowNumber][findIndexInColumnArray(COLUMN_AUTO_GLPYHS_SCORED)]      = Integer.toString(Scores.getAutonomousGlyphsScored());
        array[rowNumber][findIndexInColumnArray(COLUMN_AUTO_PARKING)]            = Boolean.toString(Scores.getParkingLevel() > 0);
        array[rowNumber][findIndexInColumnArray(COLUMN_TELE_GLYPHS_SCORED)]      = Integer.toString(Scores.getTeleOpGlyphsScored());
        array[rowNumber][findIndexInColumnArray(COLUMN_CRYPT_ROWS_COMPLETE)]     = Integer.toString(Scores.getTeleopCryptoboxRowsComplete());
        array[rowNumber][findIndexInColumnArray(COLUMN_CRYPT_COLUMNS_COMPLETE)]  = Integer.toString(Scores.getTeleopCryptoboxColumnsComplete());
        array[rowNumber][findIndexInColumnArray(COLUMN_CIPHER_COMPLETE)]         = Boolean.toString(Scores.getTeleopCipherLevel() > 0);
        array[rowNumber][findIndexInColumnArray(COLUMN_RELIC_POSITION)]          = Integer.toString(Scores.getEndgameRelicPosition());
        array[rowNumber][findIndexInColumnArray(COLUMN_RELIC_UPRIGHT)]           = Boolean.toString(Scores.getEndgameRelicOrientation() > 0);
        array[rowNumber][findIndexInColumnArray(COLUMN_ROBOT_BALANCED)]          = Boolean.toString(Scores.getEndgameRobotBalanced() > 0);
        array[rowNumber][findIndexInColumnArray(COLUMN_MINOR_PENALTIES)]         = Integer.toString(Scores.getNumMinorPenalties());
        array[rowNumber][findIndexInColumnArray(COLUMN_MAJOR_PENALTIES)]         = Integer.toString(Scores.getNumMajorPenalties());
        array[rowNumber][findIndexInColumnArray(COLUMN_TOTAL_SCORE)]             = Integer.toString(Scores.getTotalScore());
    }
}
