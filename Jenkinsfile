pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven '3.9.1'
        jdk '17'
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git (
                url: 'https://github.com/ayaguehiberus/ejercicio_01_maven.git',
                branch: 'develop'
                )

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('Test') {
            steps{
                sh 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }

    post {
        always {
            step([$class: 'Mailer',
                notifyEveryUnstableBuild: true,
                recipients: 'ayague@hiberus.com', // Reemplaza con la dirección de correo electrónico del destinatario
                sendToIndividuals: true])
        }
    }

}