# Design - Conecta Serviços KMM

## Visão Geral

O **Conecta Serviços** é um aplicativo móvel focado exclusivamente no fluxo do **profissional prestador de serviços**. O design foi pensado para facilitar a gestão de serviços, propostas e histórico de trabalhos com uma experiência intuitiva e profissional.

## Princípios de Design

1. **Foco no Profissional**: Todas as telas e fluxos são otimizados para o prestador de serviços
2. **Clareza de Informações**: Dados importantes são destacados com hierarquia visual clara
3. **Ação Rápida**: Botões e filtros permitem ações sem navegação excessiva
4. **Identidade Visual Própria**: Paleta de cores exclusiva que diferencia do GetNinja
5. **Responsividade**: Design adaptável para diferentes tamanhos de tela

## Paleta de Cores

### Cores Primárias

| Cor | Código | Uso |
|-----|--------|-----|
| Primary | `#0F3D56` | Botões, ícones, destaques |
| Secondary | `#27AE60` | Sucesso, ações positivas |
| Accent | `#F39C12` | Avisos, chamadas de atenção |
| Background | `#FFFFFF` | Fundo das telas |
| Surface | `#F7FAFC` | Superfícies elevadas, cards |

### Cores de Status

| Status | Cor de Fundo | Cor de Texto | Uso |
|--------|-------------|-------------|-----|
| Pending | `#FEF3E2` | `#B8860B` | Propostas pendentes |
| Accepted | `#E8FCF8` | `#128579` | Propostas aceitas |
| Rejected | `#FCE8E8` | `#C41E3A` | Propostas recusadas |
| Negotiating | `#E8F0FC` | `#1E40AF` | Propostas em negociação |

### Cores de Texto

| Tipo | Código | Uso |
|------|--------|-----|
| Foreground | `#102A43` | Texto principal |
| Secondary | `#5C6B7A` | Texto secundário |
| Tertiary | `#9BA1A6` | Texto terciário |

## Tipografia

- **Headlines**: Bold, 28sp (telas principais), 24sp (cards), 18sp (subtítulos)
- **Body**: Regular, 14sp (texto principal), 13sp (descrições)
- **Labels**: SemiBold, 12sp (categorias, metadados), 11sp (badges)
- **Captions**: Regular, 12sp (informações secundárias)

## Telas

### 1. Login Screen

**Objetivo**: Autenticar o profissional de forma simples e segura.

**Elementos**:
- Logo/Avatar da marca (CS em azul)
- Título: "Acesse sua operação"
- Descrição: Benefício de usar o app
- Campo de email
- Campo de senha
- Botão "Entrar" (primário)
- Info box: "Modo demonstração"

**Fluxo**:
- Usuário preenche email e senha
- Clica em "Entrar"
- Validação local
- Redirecionamento para tela de serviços

**Estados**:
- Padrão: Campos vazios, botão ativo
- Loading: Spinner no botão
- Erro: Mensagem de erro em vermelho

### 2. Services Screen (Meus Serviços)

**Objetivo**: Visualizar e gerenciar serviços cadastrados.

**Elementos**:
- Header: "MEUS SERVIÇOS" + "Serviços Cadastrados"
- Contador: "X serviço(s)"
- Lista de cards de serviço
- Botão flutuante: "+ Adicionar Novo Serviço"

**Card de Serviço**:
- Categoria (label pequena em azul)
- Título do serviço (bold)
- Status badge (Ativo/Inativo)
- Preço por hora
- Contagem de propostas
- Ação: Tap para detalhe

**Interações**:
- Tap no card: Abre detalhe do serviço
- Swipe (futuro): Ativa/desativa serviço
- Botão "+": Abre formulário de novo serviço

### 3. Proposals Screen (Propostas Recebidas)

**Objetivo**: Visualizar e gerenciar propostas de clientes.

**Elementos**:
- Header: "OPORTUNIDADES" + "Propostas Recebidas"
- Contador: "X proposta(s)"
- Filtros horizontais: Todas, Pendentes, Aceitas, Recusadas, Negociando
- Lista de cards de proposta

**Card de Proposta**:
- Serviço (label pequena em azul)
- Nome do cliente (bold)
- Valor oferecido (verde, bold)
- Status badge (com cor apropriada)
- Ação: Tap para detalhe

**Interações**:
- Tap no filtro: Filtra propostas
- Tap no card: Abre detalhe com opções de aceitar/recusar
- Swipe (futuro): Ações rápidas

### 4. History Screen (Histórico)

**Objetivo**: Visualizar histórico de trabalhos realizados.

**Elementos**:
- Header: "DESEMPENHO" + "Histórico de Trabalhos"
- Contador: "X trabalho(s)"
- Filtros horizontais: Todos, Concluídos, Em Andamento, Cancelados
- Lista de cards de trabalho

**Card de Trabalho**:
- Serviço (label pequena em azul)
- Nome do cliente (bold)
- Status badge
- Avaliação com estrelas
- Valor do trabalho (verde, bold)
- Comentário do cliente (itálico)
- Ação: Tap para detalhe

**Interações**:
- Tap no filtro: Filtra trabalhos
- Tap no card: Abre detalhe com feedback completo

### 5. Profile Screen (Perfil)

**Objetivo**: Visualizar e editar perfil profissional.

**Elementos**:
- Header: "PERFIL" + "Meu Perfil Profissional"
- Avatar com inicial do nome
- Nome (bold)
- Especialidade
- Avaliação com estrelas
- Bio/Descrição
- Seção de Estatísticas:
  - Total de trabalhos
  - Avaliação média
  - Taxa de aceitação
- Seção de Contato:
  - Email
  - Telefone (se preenchido)
- Botões:
  - "Editar Perfil" (primário)
  - "Alterar Foto" (secundário)
  - "Sair da Conta" (perigo)

**Interações**:
- Tap em "Editar Perfil": Abre formulário de edição
- Tap em "Alterar Foto": Abre seletor de galeria
- Tap em "Sair": Confirma logout

## Componentes Reutilizáveis

### Buttons

**Primary Button**
- Fundo: Primary (`#0F3D56`)
- Texto: Branco
- Altura: 56dp
- Border radius: 12dp
- Estado loading: Spinner

**Secondary Button**
- Fundo: Surface (`#F7FAFC`)
- Texto: Primary
- Border: 1dp Border color
- Altura: 56dp
- Border radius: 12dp

**Danger Button**
- Fundo: Status Rejected
- Texto: Error
- Altura: 56dp
- Border radius: 12dp

### Badges

**Status Badge**
- Fundo: Cor apropriada do status
- Texto: Cor apropriada do status
- Padding: 10dp horizontal, 6dp vertical
- Border radius: 999dp (pill)
- Font size: 11sp, Bold

**Meta Pill**
- Fundo: Surface
- Texto: Secondary
- Padding: 12dp horizontal, 8dp vertical
- Border radius: 999dp
- Font size: 12sp, SemiBold

### Cards

**Service Card**
- Border: 1dp Border color
- Border radius: 20dp
- Background: White
- Padding: 16dp
- Gap entre elementos: 12dp

**Proposal Card**
- Border: 1dp Border color
- Border radius: 20dp
- Background: White
- Padding: 16dp
- Gap entre elementos: 12dp

**Work History Card**
- Border: 1dp Border color
- Border radius: 20dp
- Background: White
- Padding: 16dp
- Gap entre elementos: 12dp

### Filter Chips

**Filter Chip**
- Não selecionado:
  - Border: 1dp Border color
  - Background: Surface
  - Texto: Secondary
- Selecionado:
  - Border: 1dp Primary
  - Background: Primary
  - Texto: White
- Border radius: 999dp
- Padding: 14dp horizontal, 8dp vertical
- Font size: 13sp, SemiBold

## Espaçamento

- **Padding padrão**: 16dp (horizontal), 24dp (vertical em headers)
- **Gap entre elementos**: 12dp (cards), 8dp (dentro de cards)
- **Border radius padrão**: 20dp (cards), 12dp (inputs), 999dp (pills)
- **Altura de botões**: 56dp
- **Altura de inputs**: 48dp

## Animações

- **Button press**: Scale 0.97
- **Loading**: Spinner infinito
- **Transição de telas**: Fade in/out
- **Status change**: Fade + scale suave

## Responsividade

- **Orientação**: Portrait (9:16)
- **Tamanhos de tela**: Adaptável de 4.5" a 6.7"
- **Padding ajustável**: Aumenta em telas maiores
- **Font scaling**: Respeita preferências do sistema

## Acessibilidade

- **Contraste**: Mínimo WCAG AA
- **Tamanho de toque**: Mínimo 48dp
- **Descrições**: Todos os ícones têm labels
- **Navegação**: Suporta navegação por teclado
- **Cores**: Não é a única forma de comunicar status

## Fluxos Principais

### Fluxo de Login

```
Login Screen
  ↓
[Validação local]
  ↓
Services Screen (Home)
```

### Fluxo de Serviços

```
Services Screen
  ↓
[Tap em serviço]
  ↓
Service Detail Screen
  ↓
[Editar/Ativar-Desativar/Deletar]
  ↓
Services Screen
```

### Fluxo de Propostas

```
Proposals Screen
  ↓
[Tap em proposta]
  ↓
Proposal Detail Screen
  ↓
[Aceitar/Recusar/Negociar]
  ↓
Proposals Screen (filtrado)
```

### Fluxo de Histórico

```
History Screen
  ↓
[Tap em trabalho]
  ↓
Work Detail Screen
  ↓
[Visualizar feedback]
  ↓
History Screen
```

### Fluxo de Perfil

```
Profile Screen
  ↓
[Tap em "Editar Perfil"]
  ↓
Edit Profile Screen
  ↓
[Salvar alterações]
  ↓
Profile Screen (atualizado)
```

## Diferenciação do GetNinja

1. **Cores**: Azul marinho + verde exclusivos vs. cores genéricas
2. **Foco**: 100% profissional vs. marketplace genérico
3. **Estrutura**: 4 abas principais vs. múltiplas navegações
4. **Informações**: Dados essenciais destacados vs. poluição visual
5. **Ações**: Botões claros e contextuais vs. ações espalhadas

## Próximas Iterações

1. **Modo escuro**: Versão dark mode com cores ajustadas
2. **Animações**: Transições mais suaves entre telas
3. **Micro-interações**: Feedback visual em mais ações
4. **Gestos**: Swipe para ações rápidas
5. **Notificações**: Visual de notificações push
6. **Chat**: Interface de mensagens integrada
