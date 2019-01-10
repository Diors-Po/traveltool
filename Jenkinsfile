pipeline {
    agent any
    stages {
        stage('prepare') {
            steps {
                git credentialsId: 'github', url: 'https://github.com/DmuMenDuo/traveltool'
            }
        }
        stage('build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps{
                sh 'mvn clean package -Dmaven.test.skip=true'

            }

        }

        stage('sonar') {
             agent {
                 docker {
                     image 'maven:3-alpine'
                     args '-v /root/.m2:/root/.m2'
                     reuseNode true
                 }
             }
             steps{
                sh 'mvn sonar:sonar -Dsonar.host.url=http://118.31.34.71:10000 -Dsonar.login=70c4dade08b1f1e3fe4f9e275d1f95990953fcba'
             }
        }

        stage('docker') {
            steps {
                sh 'docker stop traveltool'
                sh 'docker rm -f traveltool'
                sh 'docker rmi $(docker images --filter=reference="traveltool" -q)'
                sh 'docker build -t traveltool .'
            }
        }

        stage('run') {
            steps{

                sh 'docker run --name=traveltool -itd -p 9997:9997 traveltool  --spring.profiles.active=test'
            }
        }

    }

}