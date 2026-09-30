import java.util.Scanner; // usado apenas para ler o que o usuário digita no teclado

public class MenuVetor {

    // Vetor que guarda os números. Tem capacidade fixa para 10 elementos.
    static int[] vetor = new int[10];

    // Controla quantos elementos o vetor tem de verdade.
    // Ex: se quantidade = 3, as posições 0, 1 e 2 estão ocupadas e a 3 é a próxima livre.
    static int quantidade = 0;

    // Objeto que lê os dados do teclado (criado uma vez e usado em todos os métodos)
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        // O do-while repete o menu até o usuário escolher a opção 5 (encerrar)
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Inserir elementos em um vetor");
            System.out.println("2. Pesquisar um elemento no vetor");
            System.out.println("3. Excluir um elemento do vetor");
            System.out.println("4. Imprimir o vetor");
            System.out.println("5. Encerrar o programa");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1: { // Submenu de inserção
                    System.out.println("\n--- Inserir ---");
                    System.out.println("1. Na próxima posição livre do vetor");
                    System.out.println("2. Na posição informada");
                    System.out.print("Escolha: ");
                    int sub = sc.nextInt();
                    if (sub == 1) {
                        inserirNoFim();
                    } else if (sub == 2) {
                        inserirNaPosicao();
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                }
                case 2: { // Submenu de pesquisa
                    System.out.println("\n--- Pesquisar ---");
                    System.out.println("1. Pelo elemento informado");
                    System.out.println("2. Pela posição do vetor");
                    System.out.print("Escolha: ");
                    int sub = sc.nextInt();
                    if (sub == 1) {
                        pesquisarPorElemento();
                    } else if (sub == 2) {
                        pesquisarPorPosicao();
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                }
                case 3: { // Submenu de exclusão
                    System.out.println("\n--- Excluir ---");
                    System.out.println("1. Excluir o elemento informado");
                    System.out.println("2. Excluir o elemento que está na posição informada");
                    System.out.print("Escolha: ");
                    int sub = sc.nextInt();
                    if (sub == 1) {
                        excluirPorElemento();
                    } else if (sub == 2) {
                        excluirPorPosicao();
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                }
                case 4:
                    imprimir();
                    break;
                case 5:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    // Qualquer número que não esteja no menu cai aqui
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    
    // Inserir na próxima posição livre do vetor
    
    static void inserirNoFim() {
        // Se a quantidade chegou ao tamanho do vetor, não há mais espaço
        if (quantidade == vetor.length) {
            System.out.println("O vetor está cheio!");
            return; // sai do método sem inserir
        }

        System.out.print("Digite o elemento: ");
        int elemento = sc.nextInt();

        // 'quantidade' é justamente o índice da próxima posição livre
        vetor[quantidade] = elemento;
        quantidade++; // agora o vetor tem um elemento a mais
        System.out.println("Elemento inserido!");
    }

    
    // Inserir na posição que o usuário escolher

    static void inserirNaPosicao() {
        // Primeiro verifica se ainda há espaço
        if (quantidade == vetor.length) {
            System.out.println("O vetor está cheio!");
            return;
        }

        System.out.print("Digite o elemento: ");
        int elemento = sc.nextInt();
        System.out.print("Digite a posição (0 a " + quantidade + "): ");
        int posicao = sc.nextInt();

        // A posição só é válida de 0 até 'quantidade'.
        // Não dá para inserir numa posição muito à frente, pois deixaria "buracos" no vetor.
        if (posicao < 0 || posicao > quantidade) {
            System.out.println("Posição inválida!");
            return;
        }

        // Desloca os elementos uma casa para a direita, abrindo espaço.
        // Começa pelo último para não sobrescrever nenhum valor.
        // Exemplo: inserir 99 na posição 1 em [10, 20, 30]
        //   [10, 20, 30, _ ] -> [10, 20, 30, 30] -> [10, 20, 20, 30]
        for (int i = quantidade; i > posicao; i--) {
            vetor[i] = vetor[i - 1];
        }

        vetor[posicao] = elemento; // coloca o novo elemento no espaço aberto
        quantidade++;
        System.out.println("Elemento inserido!");
    }

    
    // Procura um valor e diz em qual posição ele está
    
    static void pesquisarPorElemento() {
        System.out.print("Digite o elemento que deseja pesquisar: ");
        int elemento = sc.nextInt();

        // Percorre só as posições ocupadas (0 até quantidade - 1)
        for (int i = 0; i < quantidade; i++) {
            if (vetor[i] == elemento) {
                System.out.println("Elemento encontrado na posição " + i);
                return; // achou, então não precisa continuar procurando
            }
        }

        // Se o for terminou sem dar return, o elemento não existe no vetor
        System.out.println("Elemento não encontrado.");
    }

    
    // Mostra qual elemento está em determinada posição
    
    static void pesquisarPorPosicao() {
        System.out.print("Digite a posição: ");
        int posicao = sc.nextInt();

        // Só existe elemento de 0 até quantidade - 1.
        // Sem essa validação o programa daria erro (ArrayIndexOutOfBounds).
        if (posicao < 0 || posicao >= quantidade) {
            System.out.println("Posição inválida!");
        } else {
            System.out.println("O elemento na posição " + posicao + " é: " + vetor[posicao]);
        }
    }


    // Exclui o elemento cujo valor o usuário informar
    
    static void excluirPorElemento() {
        System.out.print("Digite o elemento que deseja excluir: ");
        int elemento = sc.nextInt();

        // Procura o elemento; ao encontrar, remove e para
        // (se houver valores repetidos, apaga só o primeiro)
        for (int i = 0; i < quantidade; i++) {
            if (vetor[i] == elemento) {
                remover(i); // reaproveita o método auxiliar de remoção
                System.out.println("Elemento excluído!");
                return;
            }
        }
        System.out.println("Elemento não encontrado.");
    }

    
    // Exclui o elemento da posição que o usuário informar
    
    static void excluirPorPosicao() {
        System.out.print("Digite a posição que deseja excluir: ");
        int posicao = sc.nextInt();

        // Valida se existe elemento nessa posição
        if (posicao < 0 || posicao >= quantidade) {
            System.out.println("Posição inválida!");
        } else {
            remover(posicao);
            System.out.println("Elemento excluído!");
        }
    }

    
    // MÉTODO AUXILIAR: remove o elemento de uma posição
    // Usado pelas duas formas de exclusão para não repetir código
    
    static void remover(int posicao) {
        // Puxa cada elemento uma casa para a esquerda, tapando o buraco.
        // Exemplo: remover posição 1 de [10, 20, 30, 40]
        //   [10, 30, 30, 40] -> [10, 30, 40, 40]
        for (int i = posicao; i < quantidade - 1; i++) {
            vetor[i] = vetor[i + 1];
        }

        // Diminui a quantidade: o último valor (repetido) passa a ser ignorado
        quantidade--;
    }

    
    // Mostra todos os elementos do vetor em ordem
    
    static void imprimir() {
        if (quantidade == 0) {
            System.out.println("O vetor está vazio.");
            return;
        }

        System.out.println("Elementos do vetor:");
        // Percorre do início ao fim, imprimindo posição e valor
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Posição " + i + ": " + vetor[i]);
        }
    }
}