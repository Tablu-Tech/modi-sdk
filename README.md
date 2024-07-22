# MoDI Android SDK

## Introdução

O MoDI SDK para Android oferece uma solução abrangente para verificação de identidade digital. Este SDK permite integração com funcionalidades de prova de vida, verificação de documentos, biometria facial, comparação de selfies com fotos de documentos e verificação de face match. Ele suporta captura de documentos através da câmera ou processa imagens carregadas, além de realizar recorte de imagens, detectar a presença de MRZ (zona legível por máquina) e realizar OCR (Reconhecimento Óptico de Caracteres) nos documentos.

### Funcionalidades

* **Prova de Vida:** Verificação da presença do usuário usando biometria facial.
* **Verificação de Documentos:** Confirmação da autenticidade de documentos com leitura de MRZ e OCR.
* **Pesquisa de Subscritores:** Identificação de subscritores usando biometria facial.
* **Face Match:** Verificação do nível de similaridade entre a selfie do usuário e a foto do titular do documento.
* **Captura e Processamento de Imagens:** Captura de documentos pela câmera e processamento de imagens carregadas.

### Requisitos

* **Android 7.0 (Nougat) ou superior**
* **Gradle 5.0 ou superior**
* **Bibliotecas Nativas:** Instruções específicas para incluir as bibliotecas nativas.


## Instalação

**Adicionar o repositório JitPack**

### Adicionando ao Projeto


1. No arquivo settings.gradle onde ficam alguams confugurações do da app, adicione os seguintes repositórios:

```javascript
repositories {
    maven ("https://jitpack.io"
    maven ("https://maven.regulaforensics.com/RegulaDocumentReader")
}
```

### 


2. Para integrar o MoDI SDK ao seu projeto Android, adicione a dependência no arquivo build.gradle do seu projeto:

```javascript
dependencies {
    implementation 'com.github.Tablu-Tech:modi-sdk:1.2.2'
}
```

### Configurações Adicionais

Não se esqueça de adicionar as permissões necessárias ao seu AndroidManifest.xml:

```javascript
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## Uso Básico

### Configuração Inicial

Para iniciar o SDK, você deve configurar o ambiente e inicializar o SDK com as credenciais necessárias:

```java
import com.tablutech.modisdk.ModiSDK;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização do SDK
        MoDISDK.initialize(this, "YOUR_API_KEY");
    }
}
```


## Estruturas do SDK

### Estrutura Completa

A estrutura completa do SDK combina todas as funcionalidades em um fluxo contínuo. É ideal para processos que requerem uma verificação completa da identidade do usuário, incluindo:


1. **Prova de Vida:** Verifica se o usuário está presente.
2. **Pesquisa de Subscritores:** Verifica se o usuário já está registrado.
3. **Captura de Documento:** Solicita a captura do documento do usuário.
4. **OCR do Documento:** Realiza OCR no documento capturado.
5. **Face Match:** Compara a selfie do usuário com a foto no documento.
6. **Retorno dos Resultados:** Devolve os resultados das verificações.

## Inicialização e Uso do Processo Completo

### Componente Compose para Estrutura Completa

Para integrar a estrutura completa usando Jetpack Compose, você pode usar o componente OnboardingSDK. Aqui está como você pode usá-lo em uma tela Compose:

```javascript
@Composable
fun OnboardingScreen(navController: NavController) {

    val isSubscriberAlreadyRegistered = rememberSaveable { mutableStateOf(false) }
    val isOnboardFinished =  rememberSaveable { mutableStateOf(false) }

    //OnboardingSDK um componente que fara todo processo de onboarding e retorna todos dados e as fotos
    OnboardingSDK(onOnboardingCompleted = { navController2, documentData, portraitBitmap, faceBitmap, documentFrontBitmap, documentBackBitmap, subscriber ->
     
         // Atualiza o estado da ViewModel com os dados obtidos, todos dados sao retonados apos o processo de
        // onboarding , em caso do subscritor ja ter sido previamento registado dados como nutel , nome etc serao retornados
       
        documentData?.let { sharedViewModel.documentData = it }
        portraitBitmap?.let { sharedViewModel.updateSelfieBitmap(it) }
        faceBitmap?.let { sharedViewModel.updateFaceBitmap(it) }
        documentFrontBitmap?.let { sharedViewModel.updateFrontBitmap(it) }
        documentBackBitmap?.let { sharedViewModel.updateBackBitmap(it) }
        subscriber?.let {
            sharedViewModel.updateSubscriberInfo(it)
            isSubscriberAlreadyRegistered.value = true   
        }
        
          isSubscriberAlreadyRegistered.value = true   
    })
    
     if (isOnboardFinished .value && !isSubscriberAlreadyRegistered.value) {
           // Este If siginifica que o onboarding terminou e o subscritor foi registado com sucesso         
        }
           
     } else if (isDialogShowing.value && isSubscriberAlreadyRegistered.value) {
                // Este If siginifica que o onboarding terminou e o subscritor tinha sido previamente registado   
     }

}
```

### Detalhamento do Callback

O onOnboardingCompleted é um callback que será chamado quando o processo de onboarding for concluído. Ele fornece os seguintes parâmetros:

* **navController2**: Um controlador de navegação opcional que pode ser usado para navegação adicional.
* **documentData**: Dados do documento capturado.
* **portraitBitmap**: Imagem da selfie do usuário.
* **faceBitmap**: Imagem da face do usuário.
* **documentFrontBitmap**: Imagem da frente do documento.
* **documentBackBitmap**: Imagem do verso do documento.
* **subscriber**: Informações do assinante, se disponível.

### Processos de Navegação e Estado

* **Diálogo de Validação de Campo**: Mostra um diálogo para validação de campo se o assinante não estiver registrado.
* **Navegação Condicional**: Navega para diferentes telas com base no estado do assinante e se o diálogo está sendo exibido.

### Exemplo de Fluxo Completo

Para ilustrar, aqui está um exemplo de como o fluxo completo pode ser configurado e utilizado:

```javascript
kotlinCopy code@Composable
fun MainScreen(navController: NavController) {
    // Tela principal onde o OnboardingSDK é usado

    OnboardingScreen(navController = navController)
}
```

Neste exemplo, OnboardingScreen é a tela onde você integra o OnboardingSDK, e a navegação é gerenciada com base no estado obtido após o onboarding.

## Considerações Finais

* **Permissões**: Certifique-se de que seu aplicativo tem as permissões necessárias, como acesso à câmera e armazenamento.
* **Manuseio de Erros**: Implemente lógica para tratar possíveis erros durante o processo de onboarding.


## Estrutura Modular

## Introdução

A estrutura modular do MoDI SDK permite o uso de funcionalidades individuais, como prova de vida, verificação de documentos, OCR e face match, de forma independente. Isso é útil quando você precisa implementar apenas uma ou algumas das funcionalidades do SDK sem executar o fluxo completo.

## Estrutura Modular do SDK

### Componentes Principais

* **Prova de Vida**: Verifica se o usuário está presente.
* **Verificação de Documentos**: Captura e realiza OCR em documentos.
* **Face Match**: Compara a selfie do usuário com a foto no documento.
* **Pesquisa de Subscritores**: Verifica se o usuário já está registrado.

### Uso de Funcionalidades Individuais

Cada funcionalidade pode ser usada isoladamente. Aqui está um guia sobre como integrar e utilizar cada componente individualmente.

## 1. 1. Método passive

## **Descrição**

O método **passive** realiza a detecção de prova de vida sem exigir ações específicas do usuário. A imagem é capturada automaticamente quando o usuário está posicionado na frente da câmera.

### Exemplo de Aplicação

```javascript
import android.content.Context
import android.graphics.Bitmap

// Função para iniciar a detecção de prova de vida passiva
fun startPassiveLivenessDetection(context: Context) {
    LivenessDetection.passive(context) { bitmap ->
        // Processa a imagem capturada
        handleCapturedImage(bitmap)
    }
}

// Função para processar a imagem capturada
fun handleCapturedImage(bitmap: Bitmap) {
    // Adicione o código para lidar com a imagem capturada
    // Por exemplo, exibir a imagem em uma ImageView ou enviar para o servidor
}
```

## 2. Método active

### Descrição

O método active realiza a detecção de prova de vida exigindo que o usuário execute ações específicas, como mover o rosto ou seguir instruções visuais.

### Exemplo de Aplicação

```javascript
kotlinCopy codeimport android.content.Context
import android.graphics.Bitmap

// Função para iniciar a detecção de prova de vida ativa
fun startActiveLivenessDetection(context: Context) {
    LivenessDetection.active(context) { bitmap ->
        // Processa a imagem capturada
        handleCapturedImage(bitmap)
    }
}

// Função para processar a imagem capturada
fun handleCapturedImage(bitmap: Bitmap) {
    // Adicione o código para lidar com a imagem capturada
    // Por exemplo, exibir a imagem em uma ImageView ou enviar para o servidor
}
```


## Considerações

* **Permissões**: Verifique se sua aplicação possui permissões para acessar a câmera antes de iniciar a detecção de prova de vida.
* **Callback**: O callback fornecido em cada método é onde você processa a imagem capturada. Personalize o tratamento da imagem de acordo com as necessidades da sua aplicação.

## Conclusão

Os métodos passive e active fornecem formas flexíveis de detectar a prova de vida no MoDI SDK. Use o método que se adequa ao seu fluxo de autenticação e personalizar a experiência do usuário conforme necessário.

## Verificação de Documentos

## Descrição

A funcionalidade de **Verificação de Documentos** permite capturar imagens de documentos e realizar OCR (Reconhecimento Óptico de Caracteres) para extrair informações. A classe OCRReader fornece métodos para ler a parte frontal e traseira dos documentos.

## Métodos Disponíveis

### 1. documentReaderFront

#### Descrição

O método documentReaderFront captura a imagem da parte frontal do documento e realiza OCR para extrair as informações. Também retorna uma imagem da foto do documento e outra da foto do retrato.

#### Como Usar

```javascript
kotlinCopy codeimport android.content.Context
import android.graphics.Bitmap

// Função para iniciar a leitura do documento frontal
fun startFrontDocumentReading(context: Context) {
    OCRReader.documentReaderFront(context) { bitmap ->
        // Processa a imagem capturada do documento
        handleDocumentFrontImage(bitmap)
    }
}

// Função para processar a imagem capturada do documento frontal
fun handleDocumentFrontImage(bitmap: Bitmap) {
    // Adicione o código para lidar com a imagem capturada
    // Por exemplo, exibir a imagem em uma ImageView ou enviar para o servidor
}
```


#### Parâmetros

* **context**: O contexto da aplicação necessário para iniciar a leitura do documento.
* **onResult**: Um callback que recebe a imagem capturada e o texto extraído do documento.

#### Retorno

* **documentData**: Um mapa que contém os dados extraídos do documento, com chaves e valores representando os campos de texto.

### 2. documentReaderBack

#### Descrição

O método documentReaderBack é semelhante ao documentReaderFront, mas é usado para capturar e processar a parte traseira do documento.

#### Exemplo de Aplicação

```javascript
kotlinCopy codeimport android.content.Context
import android.graphics.Bitmap

// Função para iniciar a leitura do documento traseiro
fun startBackDocumentReading(context: Context) {
    OCRReader.documentReaderBack(context) { bitmap ->
        // Processa a imagem capturada da parte traseira do documento
        handleDocumentBackImage(bitmap)
    }
}

// Função para processar a imagem capturada da parte traseira do documento
fun handleDocumentBackImage(bitmap: Bitmap) {
    // Adicione o código para lidar com a imagem capturada
    // Por exemplo, exibir a imagem em uma ImageView ou enviar para o servidor
}
```

## Considerações

* **Permissões**: Certifique-se de que sua aplicação possui permissões para acessar a câmera e armazenamento, se necessário.
* **Qualidade da Imagem**: Para melhores resultados de OCR, garanta que a imagem capturada seja de alta qualidade e bem iluminada.
* **Tratamento de Resultados**: Utilize o mapa

  ```javascript
  documentData
  ```

   para processar e armazenar as informações extraídas dos documentos.

## Conclusão

A funcionalidade de **Verificação de Documentos** no MoDI SDK facilita a captura e processamento de informações de documentos com OCR. Use os métodos documentReaderFront e documentReaderBack para integrar essa funcionalidade na sua aplicação.


# Face Match

## Descrição

A funcionalidade de **Face Match** permite comparar duas imagens de faces para determinar o nível de similaridade entre elas. O método matchFaces da classe FaceMatch é utilizado para realizar essa comparação e retornar um valor percentual indicando o grau de correspondência.

## Método Disponível

### 1. matchFaces

#### Descrição

O método matchFaces compara duas imagens de faces e retorna a similaridade como uma porcentagem. Este método é útil para validação de identidade, verificando se duas fotos pertencem à mesma pessoa.


#### Como Usar

```javascript
import android.graphics.Bitmap

// Função para iniciar a comparação de faces
fun startFaceMatching(firstImage: Bitmap, secondImage: Bitmap) {
    FaceMatch.matchFaces(firstImage, secondImage) { similarity ->
        // Processa a similaridade entre as duas imagens
        handleFaceMatchResult(similarity)
    }
}

// Função para lidar com o resultado da comparação de faces
fun handleFaceMatchResult(similarity: Double) {
    // Adicione o código para lidar com o valor de similaridade
    // Por exemplo, exibir a porcentagem de similaridade em uma UI ou tomar ações baseadas no resultado
    println("A similaridade entre as duas imagens é: $similarity%")
}
```


#### Parâmetros

* **first**: A primeira imagem de face para comparação, representada como um

  ```javascript
  Bitmap
  ```

  .
* **second**: A segunda imagem de face para comparação, representada como um

  ```javascript
  Bitmap
  ```

  .
* **onResult**: Um callback que recebe a similaridade entre as duas imagens como uma porcentagem (valor

  ```javascript
  Double
  ```

  ).

#### Retorno

* **similarity**: Um valor percentual (

  ```javascript
  Double
  ```

  ) que indica o grau de correspondência entre as duas imagens de faces.

## Considerações

* **Qualidade das Imagens**: Para melhores resultados, assegure-se de que as imagens de faces sejam de alta qualidade e estejam bem alinhadas.
* **Tempo de Processamento**: O tempo de processamento pode variar dependendo da complexidade das imagens e da configuração do SDK.

## Conclusão

A funcionalidade de **Face Match** no MoDI SDK permite comparar faces e determinar a similaridade com alta precisão. Utilize o método matchFaces para integrar a verificação de identidade baseada em face na sua aplicação.
