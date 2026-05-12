# 💊 Sistema de Gerenciamento de Farmácia

Projeto desktop desenvolvido em **Java** com interface gráfica, banco de dados relacional e arquitetura em camadas. Criado como projeto pessoal para consolidar conhecimentos em desenvolvimento de software.

---

## 📋 Sobre o Projeto

Este sistema permite o gerenciamento de uma farmácia, possibilitando o cadastro e controle de clientes, funcionários, remédios e registros de vendas/atendimentos — tudo por meio de uma interface gráfica intuitiva.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java | Linguagem principal |
| JavaFX | Interface gráfica (telas e componentes) |
| AtlantaFX | Estilização das caixas de texto |
| CSS | Estilização dos botões |
| MySQL (Workbench) | Banco de dados relacional |

---

## 🗂️ Estrutura do Projeto

```
src/
├── classes/
│   ├── Cliente.java         # Modelo de cliente
│   ├── Funcionario.java     # Modelo de funcionário
│   ├── Remedio.java         # Modelo de remédio
│   └── Registro.java        # Modelo de registro de atendimento/venda
│
├── controllers/
│   ├── MenuController.java       # Controla a tela principal do menu
│   └── RegistrosController.java  # Controla a tela de registros
│
├── DAOs/
│   └── RegistroDAO.java     # Acesso e manipulação dos dados no banco
│
├── DatabaseConnection.java  # Gerencia a conexão com o banco de dados
├── HelloApplication.java    # Classe principal (entry point JavaFX)
└── Launcher.java            # Inicializador da aplicação

resources/
├── Tela_Menu.fxml           # Layout da tela de menu
└── Tela_Registros.fxml      # Layout da tela de registros
```

---

## 🗃️ Banco de Dados

O projeto utiliza **MySQL** e requer a execução de um script SQL para criação das tabelas.

### Como configurar:

1. Abra o **MySQL Workbench** (ou qualquer cliente MySQL)
2. Execute o arquivo `script.sql` localizado na raiz do projeto
3. Isso criará o banco de dados e todas as tabelas necessárias automaticamente

### Configuração da conexão:

No arquivo `DatabaseConnection.java`, ajuste as credenciais conforme o seu ambiente:

```java
private static final String URL = "jdbc:mysql://localhost:3306/nome_do_banco";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/)
- [JavaFX SDK](https://openjfx.io/)
- [MySQL](https://dev.mysql.com/downloads/)
- Uma IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/)

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```

2. **Configure o banco de dados**
   - Execute o `script.sql` no MySQL Workbench
   - Atualize as credenciais em `DatabaseConnection.java`

3. **Configure o JavaFX na sua IDE**
   - Adicione o JavaFX SDK como biblioteca do projeto
   - Configure os VM options:
     ```
     --module-path /caminho/para/javafx/lib --add-modules javafx.controls,javafx.fxml
     ```

4. **Execute a aplicação**
   - Rode a classe `Launcher.java`

---

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas simples:

```
[ View / FXML ]  →  [ Controller ]  →  [ DAO ]  →  [ Banco de Dados ]
     Telas             Lógica          Queries           MySQL
```

- **View (FXML + CSS + AtlantaFX):** responsável pela interface visual
- **Controllers:** recebem eventos da interface e coordenam as ações
- **DAOs (Data Access Object):** isolam toda a lógica de acesso ao banco de dados
- **Classes de modelo:** representam as entidades do sistema (Cliente, Remédio, etc.)

---

## 📬 Contato

Desenvolvido por **[Seu Nome]**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/seu-perfil)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/seu-usuario)
