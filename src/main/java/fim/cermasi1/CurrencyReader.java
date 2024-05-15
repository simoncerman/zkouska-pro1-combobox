package fim.cermasi1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyReader {
    CurrencyReader(){

    }
    public List<Currency> getCurrencies(){
        List<Currency> currencies = new ArrayList<>();
        try {
            List<String> currenciesLines = readFile();

            // Do for each currency from the third line
            for (int i = 2; i < currenciesLines.size(); i++) {
                String[] currency = currenciesLines.get(i).split("\\|");
                String country = currency[0];
                String currencyName = currency[1];
                int quantity = Integer.parseInt(currency[2]);
                String code = currency[3];
                double course = Double.parseDouble(currency[4].replace(",","."));
                Currency c = new Currency(country, currencyName, quantity, code, course);
                currencies.add(c);
            }
            return currencies;
        }
        catch (IOException ex){
            System.out.println("Error reading currencies");
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public List<String> readFile() throws IOException {
        String fileName = "900042.txt"; // Replace with your actual filename
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> text = new ArrayList<>();

        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                text.add(line);
            }
            br.close();
            fr.close();
        } else {
            System.out.println("File not found: " + fileName);
        }
        return text;
    }
}
