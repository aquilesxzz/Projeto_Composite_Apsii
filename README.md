# Sistema de Gerenciamento de Arquivos e Pastas

Projeto desenvolvido em Java para a disciplina **Análise e Projeto de Sistemas II (APS II)**, com o objetivo de aplicar o padrão de projeto **Composite** em um sistema de gerenciamento de arquivos e pastas.

## Sobre o projeto

O sistema simula uma estrutura de diretórios, permitindo criar pastas e arquivos, organizar pastas dentro de outras pastas e visualizar a estrutura hierárquica. Também é possível calcular o tamanho total de uma pasta considerando todos os seus elementos e remover arquivos ou pastas.

Os dados são persistidos em um arquivo por meio de serialização de objetos Java. Assim, a estrutura criada permanece disponível quando o programa é encerrado e executado novamente.

## Funcionalidades

- Criar arquivos informando nome e tamanho em KB.
- Criar pastas e subpastas.
- Listar o conteúdo de uma pasta.
- Exibir a árvore completa de diretórios.
- Calcular recursivamente o tamanho total de uma pasta.
- Remover arquivos e pastas.
- Validar entradas, nomes duplicados e operações inválidas.
- Impedir que a pasta raiz seja removida ou que a estrutura forme ciclos.
- Salvar e carregar os dados automaticamente.

## Padrão de projeto: Composite

O padrão **Composite** permite tratar objetos individuais e composições de objetos de maneira uniforme, por meio de uma abstração comum.

Neste projeto, ele representa a estrutura hierárquica de um sistema de arquivos:

- **Component (ElementoSistema):** interface comum que define as operações getNome(), getTamanho() e exibir().
- **Leaf (Arquivo):** representa um arquivo individual. Possui nome e tamanho e não contém outros elementos.
- **Composite (Pasta):** representa uma pasta, que pode armazenar arquivos e outras pastas por meio de uma lista de ElementoSistema.

Dessa forma, tanto arquivos quanto pastas podem ser tratados por meio da interface ElementoSistema. O cálculo do tamanho e a exibição da estrutura são realizados de forma recursiva: uma pasta soma os tamanhos de seus elementos e solicita que cada elemento exiba sua própria representação.

### Por que o Composite foi escolhido?

Uma estrutura de arquivos é naturalmente hierárquica: pastas podem conter arquivos e outras pastas, em diferentes níveis. O Composite se encaixa nesse cenário porque permite representar folhas e grupos com a mesma interface, simplificando operações recursivas e evitando que o código cliente precise tratar cada nível da hierarquia de maneira completamente diferente.

### Outros padrões que poderiam ser utilizados

Outros padrões poderiam complementar ou atender partes do sistema, mas não representam tão diretamente a hierarquia de arquivos e pastas:

- **Iterator** poderia ser usado para percorrer os elementos de uma pasta de maneira padronizada.
- **Visitor** poderia separar operações realizadas sobre arquivos e pastas, caso o sistema tivesse muitas operações diferentes.
- **Factory Method** poderia centralizar a criação de arquivos e pastas, caso as regras de instanciação se tornassem mais complexas.

Esses padrões poderiam ser úteis em uma evolução do projeto, mas o Composite é o mais adequado para modelar sua estrutura principal.

## Estrutura do projeto

~~~text
Projeto_Composite_Apsii/
├── .gitignore
├── README.md
└── SistemaArquivosComposite/
    └── src/
        ├── ElementoSistema.java
        ├── Arquivo.java
        ├── Pasta.java
        ├── GerenciadorArquivos.java
        ├── RepositorioSistema.java
        ├── RepositorioSerializado.java
        └── Main.java
~~~

### Responsabilidade das classes

| Classe | Responsabilidade |
|---|---|
| ElementoSistema | Define a interface comum dos elementos do sistema de arquivos. |
| Arquivo | Implementa um arquivo individual (Leaf). |
| Pasta | Armazena outros elementos e realiza operações recursivas (Composite). |
| GerenciadorArquivos | Coordena as operações de criação, remoção, localização, listagem e cálculo. |
| RepositorioSistema | Define o contrato para salvar e carregar a estrutura. |
| RepositorioSerializado | Implementa a persistência usando serialização Java. |
| Main | Apresenta o menu de interação pelo terminal e recebe os dados do usuário. |

## Princípios SOLID aplicados

- **S — Single Responsibility Principle (Responsabilidade Única):** as classes possuem responsabilidades separadas. Por exemplo, Arquivo representa arquivos, Pasta gerencia seus elementos, GerenciadorArquivos coordena as operações e o repositório cuida da persistência.
- **O — Open/Closed Principle (Aberto/Fechado):** a abstração ElementoSistema permite adicionar novos tipos de elementos que implementem o contrato, sem precisar alterar a lógica que trabalha com a abstração, embora novas regras específicas possam exigir ajustes.
- **L — Liskov Substitution Principle (Substituição de Liskov):** objetos Arquivo e Pasta podem ser utilizados onde se espera um ElementoSistema, respeitando as operações definidas pela interface.
- **I — Interface Segregation Principle (Segregação de Interfaces):** ElementoSistema apresenta um contrato pequeno, com operações essenciais compartilhadas por arquivos e pastas.
- **D — Dependency Inversion Principle (Inversão de Dependência):** GerenciadorArquivos depende da abstração RepositorioSistema, e não diretamente da implementação concreta RepositorioSerializado.

## Tecnologias

- **Linguagem:** Java
- **Versão recomendada:** JDK 11 ou superior
- **Interface:** terminal/console
- **Persistência:** serialização de objetos Java
- **IDE utilizada no desenvolvimento:** IntelliJ IDEA

## Como executar

É necessário ter o **JDK 11 ou superior instalado**. Os comandos abaixo devem ser executados a partir da raiz do repositório.

### Windows (Prompt de Comando)

~~~bat
if not exist SistemaArquivosComposite\out mkdir SistemaArquivosComposite\out
javac -d SistemaArquivosComposite\out SistemaArquivosComposite\src\*.java
java -cp SistemaArquivosComposite\out Main
~~~

### Linux, macOS ou Git Bash

~~~bash
mkdir -p SistemaArquivosComposite/out
javac -d SistemaArquivosComposite/out SistemaArquivosComposite/src/*.java
java -cp SistemaArquivosComposite/out Main
~~~

O programa inicia com um menu no terminal. Siga as opções apresentadas para criar, listar, remover e consultar arquivos e pastas.

> Na primeira execução, o sistema cria a pasta raiz padrão chamada Documentos. O arquivo dados.dat é gerado automaticamente para armazenar os dados e não precisa ser enviado ao repositório.

## Persistência dos dados

A persistência é realizada pela classe RepositorioSerializado, que implementa a interface RepositorioSistema. Ao realizar operações que modificam a estrutura, o gerenciador solicita que a raiz seja salva. Ao iniciar o programa, os dados são carregados novamente. Caso o arquivo de dados ainda não exista, uma nova pasta raiz é criada.

## Observações

- Os tamanhos dos arquivos são informados e exibidos em **KB**; não representam necessariamente o tamanho físico de arquivos reais no disco.
- O sistema é uma aplicação didática em memória, com persistência da estrutura por serialização, e não manipula diretamente os arquivos do sistema operacional.
- A pasta out/, arquivos .class, configurações locais da IDE e o arquivo de dados gerado durante a execução são ignorados pelo Git.

## Contexto acadêmico

Trabalho desenvolvido para a disciplina **Análise e Projeto de Sistemas II (APS II)**, com foco na implementação e demonstração do padrão de projeto Composite e na aplicação dos princípios SOLID.
