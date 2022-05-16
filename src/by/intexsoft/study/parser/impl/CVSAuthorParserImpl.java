package by.intexsoft.study.parser.impl;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.parser.AuthorParser;

import java.util.ArrayList;
import java.util.List;

public class CVSAuthorParserImpl implements AuthorParser {
    @Override
    public Author toAuthor(String line) {
        String [] parser = line.split(",");
        Author author = new Author(parser[0], parser[1], parser[2], parser[3]);
        return author;
    }

    @Override
    public List<Author> toAuthors(List<String> lines) {
        List<Author> authorList = new ArrayList<>();
        for(int i = 0; i <lines.size(); i++){
            authorList.add(toAuthor(lines.get(i)));
        }
        return authorList;
    }

    @Override
    public String fromAuthor(Author author) {
        String delimeter = ",";
        StringBuilder sb = new StringBuilder("");
        String line = (sb.append(author.getAuthorID()+delimeter+
                author.getAuthorName()+delimeter+
                author.getPhoneNumber()+delimeter+
                author.getEmail()+"\n")).toString();
        return line;
    }

    @Override
    public List<String> fromAuthors(List<Author> authorsList) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < authorsList.size(); i++){
            lines.add(fromAuthor(authorsList.get(i)));
        }
        return lines;
    }
}
