
package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReader {

    private ExcelReader() {

    }

    public static Object[][] readSheet(String resourcePath, String sheetName) {
        try (InputStream file = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (file == null) {
                throw new IllegalStateException(
                        "Excel file not found on classpath: " + resourcePath
                );
            }

            try (Workbook workbook = WorkbookFactory.create(file)) {
                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    throw new IllegalStateException(
                            "Sheet '" + sheetName + "' not found in " + resourcePath
                    );
                }

                DataFormatter formatter = new DataFormatter();
                int columnCount = sheet.getRow(0).getLastCellNum();
                List<Object[]> rows = new ArrayList<>();

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);

                    if (isEmpty(row, formatter)) {
                        continue;
                    }

                    Object[] values = new Object[columnCount];

                    for (int j = 0; j < columnCount; j++) {
                        values[j] = formatter.formatCellValue(row.getCell(j)).trim();
                    }

                    rows.add(values);
                }

                return rows.toArray(new Object[0][]);
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read Excel file: " + resourcePath, e
            );
        }
    }

    private static boolean isEmpty(Row row, DataFormatter formatter) {
        if (row == null) {
            return true;
        }

        for (Cell cell : row) {
            if (!formatter.formatCellValue(cell).trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
