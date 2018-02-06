package controller;

import model.ResultParam;
import model.ResultParams;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final String FILE = "src/main/resources/analiticoOcupacaoRede-search.xml";
    static final int ORACLE_CHARACTER_LIMIT = 30;
    static final String BR = "==================================";
    static final String MENU = "" +
            "============= M E N U ============\n" +
            "|| [1] para gerar mockSQL       ||\n" +
            "|| [2] para listar field names  || \n" +
            "|| [3] para validar field names || \n" +
            "==================================";

    public static void main(String[] args) {
        try {
            List<ResultParam> list = unmarshallXml(FILE);
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println(MENU);
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println(generateMockSQL(list));
                        break;
                    case 2:
                        printFieldNames(list);
                        break;
                    case 3:
                        validateFieldNames(list);
                }
                System.out.println(BR);
            }
            while (scanner.hasNextLine());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static List<ResultParam> unmarshallXml(String FILE) throws JAXBException {
        File file = new File(FILE);
        JAXBContext jaxbContext = JAXBContext.newInstance(ResultParams.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ResultParams rps = (ResultParams) jaxbUnmarshaller.unmarshal(file);

        return rps.getResultParams();
    }

    private static String generateMockSQL(List<ResultParam> rps) {
        String sql = "SELECT ";

        for (ResultParam rp : rps) {
            String fieldName = rp.getFields().get(0).getField();
            sql += "1 as " + fieldName + ", ";
        }
        sql = sql.substring(0, sql.length() - 2); //remove última vírgula
        sql += " FROM DUAL";

        sql = new BasicFormatterImpl().format(sql); //formata
        return sql;
    }

    private static void printFieldNames(List<ResultParam> rps) {
        for (ResultParam rp : rps) {
            System.out.println(rp.getFields().get(0).getField());
        }
        System.out.println("TOTAL: " + rps.size());
    }

    private static void validateFieldNames(List<ResultParam> rps) {
        int erros = 0;
        int limite = ORACLE_CHARACTER_LIMIT;
        for (ResultParam rp : rps) {
            if (rp.getName().length() >= limite) {
                System.out.println("Tamanho não permitido encontrado: " + rp.getName().length() + " caracteres de '" + rp.getName() + "'");
                System.out.println("O result param: '" + rp.getName() + "' tem mais de " + limite + " caracteres(máximo permitio como identificador Oracle).");
                erros++;
            }
        }
        System.out.println("NÚMERO DE ERROS: " +  erros);
        System.out.println("FIM DA VALIDAÇÃO.");
    }
}
