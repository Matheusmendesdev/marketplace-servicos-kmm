# Conecta Serviços - KMM

Aplicativo móvel multiplataforma em **Kotlin Multiplatform Mobile (KMM)** para gerenciamento de serviços profissionais, com suporte nativo para iOS e Android.

## Visão Geral

O **Conecta Serviços** é um marketplace focado no fluxo do profissional prestador de serviços. O aplicativo permite que profissionais:

- **Cadastrem e gerenciem seus serviços** com preços, categorias e disponibilidade
- **Recebam e avaliem propostas** de clientes interessados
- **Acompanhem histórico de trabalhos** com avaliações e comentários
- **Mantenham seu perfil profissional** atualizado com estatísticas

## Arquitetura

O projeto segue a arquitetura **KMM** com separação clara entre código compartilhado e específico de plataforma:

```
marketplace-servicos-kmm/
├── shared/                          # Código compartilhado (Kotlin Multiplatform)
│   ├── src/commonMain/
│   │   ├── kotlin/com/conectaservicos/
│   │   │   ├── model/              # Modelos de dados (Service, Proposal, etc)
│   │   │   ├── data/               # Dados mock e repositórios
│   │   │   ├── viewmodel/          # ViewModels compartilhados
│   │   │   └── ui/
│   │   │       ├── theme/          # Tema e cores
│   │   │       ├── components/     # Componentes Compose reutilizáveis
│   │   │       └── screens/        # Telas principais
│   ├── src/androidMain/            # Código específico Android
│   └── src/iosMain/                # Código específico iOS
├── androidApp/                      # Aplicativo Android
│   ├── src/main/
│   │   ├── kotlin/                 # Activity e configuração Android
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── iosApp/                         # Aplicativo iOS (SwiftUI)
│   ├── ConectaServicos/
│   ├── ConectaServicos.xcodeproj/
│   └── Podfile
├── gradle/                         # Configuração Gradle
├── build.gradle.kts                # Build root
└── settings.gradle.kts             # Settings root
```

## Tecnologias

- **Kotlin Multiplatform Mobile (KMM)** - Compartilhamento de código entre plataformas
- **Jetpack Compose** - UI moderna e reativa para Android
- **SwiftUI** - UI nativa para iOS
- **Kotlin Coroutines** - Programação assíncrona
- **Kotlinx Serialization** - Serialização JSON
- **Lifecycle ViewModel** - Gerenciamento de estado

## Estrutura de Dados

### Modelos Principais

**Service** - Serviço cadastrado pelo profissional
```kotlin
data class Service(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val pricePerHour: Double,
    val estimatedHours: Int,
    val proposalsCount: Int,
    val isActive: Boolean,
    val createdAt: String
)
```

**Proposal** - Proposta de cliente para um serviço
```kotlin
data class Proposal(
    val id: String,
    val serviceName: String,
    val description: String,
    val client: Client,
    val offeredValue: Double,
    val suggestedValue: Double,
    val proposedDate: String,
    val proposedTime: String,
    val location: String?,
    val status: ProposalStatus,
    val createdAt: String
)
```

**WorkHistory** - Histórico de trabalhos realizados
```kotlin
data class WorkHistory(
    val id: String,
    val serviceName: String,
    val client: Client,
    val value: Double,
    val completedDate: String,
    val status: WorkStatus,
    val rating: Double,
    val comment: String
)
```

**ProfessionalProfile** - Perfil do profissional
```kotlin
data class ProfessionalProfile(
    val id: String,
    val name: String,
    val specialty: String,
    val bio: String,
    val rating: Double,
    val totalJobs: Int,
    val acceptanceRate: Double,
    val email: String,
    val phone: String?,
    val joinedDate: String
)
```

## Telas Principais

### 1. **Login Screen**
- Autenticação local com email e senha
- Modo demonstração com dados pré-preenchidos
- Validação de formulário

### 2. **Services Screen (Meus Serviços)**
- Lista de serviços cadastrados
- Status ativo/inativo
- Preço por hora e contagem de propostas
- Botão para adicionar novo serviço

### 3. **Proposals Screen (Propostas Recebidas)**
- Lista de propostas com filtros por status
- Filtros: Pendentes, Aceitas, Recusadas, Negociando
- Visualização de cliente, valor e data proposta
- Ação rápida para aceitar/recusar

### 4. **History Screen (Histórico)**
- Histórico de trabalhos realizados
- Filtros por status: Concluído, Em Andamento, Cancelado
- Avaliações de clientes com comentários
- Estatísticas de desempenho

### 5. **Profile Screen (Perfil)**
- Informações do profissional
- Estatísticas: total de trabalhos, avaliação média, taxa de aceitação
- Contato: email e telefone
- Opções de editar perfil e sair

## ViewModels

### AuthViewModel
Gerencia autenticação local do usuário.

```kotlin
class AuthViewModel : ViewModel() {
    fun login(email: String, password: String)
    fun logout()
}
```

### ServiceViewModel
Gerencia lista de serviços, adição e ativação/desativação.

```kotlin
class ServiceViewModel : ViewModel() {
    fun selectService(id: String)
    fun toggleServiceStatus(id: String)
    fun addService(...)
    fun deleteService(id: String)
}
```

### ProposalViewModel
Gerencia propostas com filtros por status.

```kotlin
class ProposalViewModel : ViewModel() {
    fun selectProposal(id: String)
    fun setFilterStatus(status: ProposalStatus?)
    fun acceptProposal(id: String)
    fun rejectProposal(id: String)
}
```

### HistoryViewModel
Gerencia histórico de trabalhos e estatísticas.

```kotlin
class HistoryViewModel : ViewModel() {
    fun selectWork(id: String)
    fun setFilterStatus(status: WorkStatus?)
}
```

### ProfileViewModel
Gerencia perfil profissional e edição.

```kotlin
class ProfileViewModel : ViewModel() {
    fun updateProfile(name: String, specialty: String, bio: String, phone: String?)
    fun getAverageRating(): Double
    fun getCompletedJobsCount(): Int
}
```

## Tema e Cores

O aplicativo usa uma paleta de cores exclusiva com identidade visual própria:

- **Primary**: `#0F3D56` - Azul marinho (ações principais)
- **Secondary**: `#27AE60` - Verde (sucesso)
- **Accent**: `#F39C12` - Laranja (destaque)
- **Background**: `#FFFFFF` - Branco
- **Surface**: `#F7FAFC` - Cinza claro
- **Foreground**: `#102A43` - Cinza escuro (texto)

### Status Colors

- **Pending**: Amarelo claro
- **Accepted**: Verde claro
- **Rejected**: Vermelho claro
- **Negotiating**: Azul claro

## Componentes Reutilizáveis

- `PrimaryButton` - Botão principal com loading
- `SecondaryButton` - Botão secundário
- `DangerButton` - Botão de ação perigosa
- `StatusBadge` - Badge de status
- `MetaPill` - Etiqueta de metadados
- `ServiceCard` - Card de serviço
- `ProposalCard` - Card de proposta
- `FilterChip` - Chip de filtro

## Dados Mock

O aplicativo inclui dados mock para demonstração:

- **3 serviços** cadastrados com diferentes categorias
- **3 propostas** em diferentes status
- **3 trabalhos** no histórico com avaliações
- **1 perfil profissional** completo

## Próximos Passos

1. **Persistência Real**: Integrar banco de dados local (Room para Android, SQLite para iOS)
2. **Backend Integration**: Conectar com API REST para sincronização de dados
3. **Autenticação Segura**: Implementar OAuth 2.0 e token refresh
4. **Notificações Push**: Integrar Firebase Cloud Messaging
5. **Chat/Mensagens**: Sistema de comunicação entre profissional e cliente
6. **Pagamentos**: Integração com gateway de pagamento
7. **Testes**: Testes unitários e de integração

## Desenvolvimento

### Pré-requisitos

- Kotlin 1.9.21+
- Gradle 8.2.0+
- Android SDK 34+
- Xcode 15+ (para iOS)
- Java 11+

### Build Android

```bash
./gradlew :androidApp:build
```

### Build iOS

```bash
cd iosApp
pod install
open ConectaServicos.xcworkspace
```

### Executar Testes

```bash
./gradlew test
```

## Licença

Propriedade da Conecta Serviços © 2024
