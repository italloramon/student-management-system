
# Pre-requisites
Documento
```
Observações:
Alunos, professores e responsáveis são usuários do sistema, logo todos têm atributos em comum. Então todos eles herdam alguns atributos da super classe usuário. 
Utilizamos polimorfismos e generics de criação, remoção e atualização de usuários (professor, aluno e responsável).

Antes tínhamos funções separadas para criação, remoção e atualização de usuários (professor, aluno e responsável) feitas em cada controller. Agora, criamos um controller principal (classe abstrata) que vai ter as funções de adicionar, remover e atualizar. E os controllers herdam desse controller principal e a especificação do tipo é feito usando generics.

```
- You will need the following:

    -> [Apache Maven 4.0.0](https://maven.apache.org/download.cgi)

    -> [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

# How to run the API
- You have two options, first is download the code as zip, second is run the following command (note: you'll need git installed to use this):

    ``git clone https://github.com/italloramon/student-management-system.git ``

- Now you'll need to access the folder

    ``cd student-management-system/back-end/student-management-system/``
    
- And then run the command in terminal:

    ``mvn spring-boot:run ``

- Now you can go to [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

