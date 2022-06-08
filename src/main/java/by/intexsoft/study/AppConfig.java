package by.intexsoft.study;

import by.intexsoft.study.fileUtils.CSVReader;
import by.intexsoft.study.fileUtils.CSVWriter;
import by.intexsoft.study.fileUtils.impl.CSVReaderImpl;
import by.intexsoft.study.fileUtils.impl.CSVWriterImpl;
import by.intexsoft.study.filters.OperatorManager;
import by.intexsoft.study.orders.OrderManager;
import by.intexsoft.study.parser.AuthorParser;
import by.intexsoft.study.parser.BookParser;
import by.intexsoft.study.storage.AuthorStorageWorker;
import by.intexsoft.study.storage.BookStorageWorker;
import by.intexsoft.study.storage.impl.AuthorStorageWorkerImpl;
import by.intexsoft.study.storage.impl.BookStorageWorkerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "by.intexsoft.study")
@PropertySource(value = "classpath:storage.properties")
public class AppConfig {

   @Autowired
   Environment environment;

    @Bean
    public CSVReader bookReader(){
        CSVReader out = new CSVReaderImpl(environment.getProperty("bookCsvFilename"));
        return out;
    }

    @Bean
    public CSVWriter bookWriter(){
        CSVWriter out = new CSVWriterImpl(environment.getProperty("bookCsvFilename"));
        return out;
    }

    @Bean
    public BookStorageWorker bookStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, BookParser bookParser, CSVReader bookReader, CSVWriter bookWriter){
        BookStorageWorker out = new BookStorageWorkerImpl(operatorManager, orderManager, bookReader, bookWriter, bookParser);
        return out;
    }

   @Bean
    public CSVReader authorReader(){
        CSVReader out = new CSVReaderImpl(environment.getProperty("authorCsvFilename"));
        return out;
    }

    @Bean
    public CSVWriter authorWriter(){
        CSVWriter out = new CSVWriterImpl(environment.getProperty("authorCsvFilename"));
        return out;
    }

   @Bean
    public AuthorStorageWorker authorStorageWorkerImpl(OperatorManager operatorManager, OrderManager orderManager, AuthorParser authorParser, CSVReader authorReader, CSVWriter authorWriter){
        AuthorStorageWorker out = new AuthorStorageWorkerImpl(operatorManager, orderManager, authorReader, authorWriter, authorParser);
        return out;
    }

}
