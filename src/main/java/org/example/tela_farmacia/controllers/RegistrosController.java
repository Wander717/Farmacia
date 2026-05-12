package org.example.tela_farmacia.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.*;
import org.example.tela_farmacia.DAOs.RegistroDAO;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RegistrosController {

    @FXML
    private TableView<Registro> tabela_Registros;

    @FXML
    private TableColumn<Registro, Integer> col_IdRegistro;

    @FXML
    private TableColumn<Registro, String> col_NomeCliente;

    @FXML
    private TableColumn<Registro, Integer> col_IdadeCliente;

    @FXML
    private TableColumn<Registro, String> col_NomeRemedio;

    @FXML
    private TableColumn<Registro, String> col_TipoRemedio;

    @FXML
    private TableColumn<Registro, Integer> col_QuantRemedio;

    @FXML
    private TableColumn<Registro, String> col_NomeFuncionario;

    @FXML
    private Button btn_VoltarMenu;


    @FXML
    public void initialize() {
        tabela_Registros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tabela_Registros.setEditable(false);
        tabela_Registros.getSelectionModel().setCellSelectionEnabled(true);

        configurarColunas();
        carregarDados();
    }

    //FAZ O POSICIONAMENTO DAS COLUNAS
    private void configurarColunas() {
        col_IdRegistro.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());

        col_NomeCliente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCliente().getNome()));

        col_IdadeCliente.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getCliente().getIdade()).asObject());

        col_NomeRemedio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRemedio().getNome()));

        col_TipoRemedio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRemedio().getTipo()));

        col_QuantRemedio.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getRemedio().getQuantidade()).asObject());

        col_NomeFuncionario.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFuncionario().getNome()));
    }

    //CARREGA OS DADOS PARA SEREM EXIBIDOS NA TABELA
    private void carregarDados() {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            RegistroDAO dao = new RegistroDAO(conexao);
            List<Registro> lista = dao.listarTodos();
            ObservableList<Registro> obsLista = FXCollections.observableArrayList(lista);
            tabela_Registros.setItems(obsLista);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao carregar dados: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    //MÉTODO QUE REALIZA A EXCLUSÃO DE UM REGISTRO
    @FXML
    private void excluirLinhaSelecionada() {
        Registro selecionado = tabela_Registros.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Exclusão");
            alerta.setContentText("Deseja realmente excluir o registro do cliente " + selecionado.getCliente().getNome() + "?");

            if (alerta.showAndWait().get() == ButtonType.OK) {
                try (Connection conexao = DatabaseConnection.getConnection()) {
                    RegistroDAO dao = new RegistroDAO(conexao);
                    dao.deletar(selecionado.getId());

                    carregarDados(); // Atualiza a tabela
                    mostrarAlerta("Sucesso", "Registro excluído com sucesso!", Alert.AlertType.INFORMATION);
                } catch (SQLException e) {
                    mostrarAlerta("Erro", "Erro ao excluir: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Aviso", "Por favor, selecione um registro na tabela primeiro!", Alert.AlertType.WARNING);
        }
    }

    //MÉTODO QUE POSSIBILITA A EDIÇÃO DE UMA DETERMINADA LINHA EM UMA COLUNA
    @FXML
    private void editarLinhaSelecionada() {

        Registro selecionado =
                tabela_Registros.getSelectionModel()
                        .getSelectedItem();

        if (selecionado != null) {

            tabela_Registros.setEditable(true);

            editar();

            TablePosition<Registro, ?> posicao =
                    tabela_Registros.getFocusModel()
                            .getFocusedCell();

            if (posicao.getTableColumn() != null) {

                tabela_Registros.edit(
                        posicao.getRow(),
                        posicao.getTableColumn()
                );
            }

        } else {

            mostrarAlerta(
                    "Aviso",
                    "Selecione um registro para editar!",
                    Alert.AlertType.WARNING
            );
        }
    }

    //VOLTA PARA A TELA DE INSERIR NOVOS REGISTROS
    @FXML
    private void voltarMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/Tela_Menu.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) btn_VoltarMenu.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MÉTODO ÚNICO PARA EXIBIÇÃO DE ALERTAS
    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    //MÉTODO QUE POSSIBILITA A EDIÇÃO DE INFORMAÇÕES JÁ ADICIONADAS AO BANCO DE DADOS
    private void editar() {
        col_NomeCliente.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeCliente.setOnEditCommit(event -> {
            Registro registro = event.getRowValue();
            registro.getCliente().setNome(event.getNewValue());

            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarCliente(
                        registro.getCliente()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });

        col_IdadeCliente.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_IdadeCliente.setOnEditCommit(event -> {
            Registro registro = event.getRowValue();
            registro.getCliente().setIdade(event.getNewValue());
            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarCliente(
                        registro.getCliente()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
        col_NomeRemedio.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeRemedio.setOnEditCommit( event -> {
            Registro registro = event.getRowValue();
            registro.getRemedio().setNome(event.getNewValue());
            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarRemedio(
                        registro.getRemedio()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
        col_TipoRemedio.setCellFactory(TextFieldTableCell.forTableColumn());
        col_TipoRemedio.setOnEditCommit( event -> {
            Registro registro = event.getRowValue();
            registro.getRemedio().setTipo(event.getNewValue());
            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarRemedio(
                        registro.getRemedio()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
        col_QuantRemedio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_QuantRemedio.setOnEditCommit(event -> {
            Registro registro = event.getRowValue();
            registro.getRemedio().setQuantidade(event.getNewValue());
            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarRemedio(
                        registro.getRemedio()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
        col_NomeFuncionario.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeFuncionario.setOnEditCommit(event -> {
            Registro registro = event.getRowValue();
            registro.getFuncionario().setNome(event.getNewValue());
            try (Connection conexao =
                         DatabaseConnection.getConnection()) {

                RegistroDAO dao =
                        new RegistroDAO(conexao);

                dao.atualizarFuncionario(
                        registro.getFuncionario()
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
    }
}