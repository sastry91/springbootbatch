package com.sastry.spring.bootbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonTasklet implements Tasklet {

    @Autowired
    private DataSource dataSource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        createPersonFile();

        return RepeatStatus.FINISHED;
    }

    private void createPersonFile() throws SQLException, IOException {


        Connection connection = dataSource.getConnection();
        String filename = "src/main/resources/person-text-file.txt";
        FileWriter fw = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");

            fw = new FileWriter(filename, true); //the true will append the new data
            while (rs.next()) {
                fw.write(rs.getInt(1) + "|" + rs.getString(2) +
                        "|" + rs.getString(3) + "|" + rs.getString(4) + "\n");//appends the string to the file

            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fw.close();
            connection.close();
        }


    }
}
