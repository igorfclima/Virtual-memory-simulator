# Simulador de Gerenciamento de Memória Virtual

Este projeto consiste na implementação de um simulador de sistema de gerenciamento de **Memória Virtual** utilizando **Paginação por Demanda**. O objetivo é simular e comparar o desempenho de diferentes políticas de substituição de páginas, contabilizando o número de *Page Faults* (falhas de página), o tempo de execução e o estado final do Swap.

Trabalho desenvolvido para a disciplina de **Sistemas Operacionais** da Pontifícia Universidade Católica de Minas Gerais (PUC Minas).

## 📋 Funcionalidades

**Simulação de Hardware:** Configuração flexível de Memória Física (RAM), Memória Virtual e Tamanho de Página.
**Cálculo Automático:** Determinação automática do Tamanho da Página ($S_P$), Número de Frames e Tamanho Mínimo de Swap.
**Políticas de Substituição:** Implementação dos 4 algoritmos exigidos:
    1.  **FIFO** (First-In, First-Out)
    2.  **RAND** (Aleatório)
    3.  **LRU** (Least Recently Used)
    4.  **MIN/OPT** (Algoritmo Ótimo)
**Saída Padronizada:** Formatação estrita conforme especificação para correção automática.

## 🚀 Estrutura do Projeto (Modularização)

O código foi desenvolvido em **Java**, utilizando o padrão de projeto **Strategy** para os algoritmos de substituição.

```text
src/br/so/
├── Main.java                 # Ponto de entrada (Leitura de stdin e orquestração)
├── model/
│   └── SystemConfig.java     # Representa e calcula parâmetros do sistema (M, V, A, P)
├── core/
│   ├── MemoryManager.java    # Gerencia a RAM (Frames), Swap e contabiliza Page Faults
│   └── Simulator.java        # Motor de execução que roda as sequências
└── algorithms/               # Implementação das Políticas (Strategy Pattern)
    ├── PageReplacementStrategy.java  # Interface comum (Contrato)
    ├── FIFOStrategy.java             # Fila (Queue)
    ├── LRUStrategy.java              # Histórico de acesso (List)
    ├── RandomStrategy.java           # Escolha aleatória
    └── OptimalStrategy.java          # Análise futura (Lookahead)
```
## 🛠️ Como Compilar e Executar

O projeto não requer ferramentas externas (Maven/Gradle), apenas o JDK padrão. Siga os passos abaixo para compilar e executar via terminal.

### 1. Compilação
Abra o terminal na raiz do projeto e execute o comando abaixo para compilar todos os arquivos `.java` para a pasta `bin`:

**No Windows (PowerShell):**
```powershell
# Cria o diretório bin caso não exista
New-Item -ItemType Directory -Force -Path bin

# Compila todos os arquivos java encontrados em src
$arquivos = Get-ChildItem -Path src -Recurse -Filter *.java
javac -d bin $arquivos.FullName
```

**No Linux/Mac/WSL:**
```bash
# Cria o diretório e compila
mkdir -p bin
javac -d bin $(find src -name "*.java")
```

### 2. Execução
Para rodar o simulador, você deve passar um arquivo de entrada (ex: teste.txt localizado na pasta entradas) via redirecionamento de entrada padrão (stdin).

No **Windows (PowerShell)**: ⚠️ O PowerShell não suporta o operador < nativamente. Utilize o comando cmd /c para contornar:
```powershell
cmd /c "java -cp bin Main < entradas/teste.txt"
```

**No Linux/Mac/CMD:**
```bash
java -cp bin Main < entradas/teste.txt
```

**Nota:** Certifique-se de estar na raiz do projeto ao executar os comandos.

