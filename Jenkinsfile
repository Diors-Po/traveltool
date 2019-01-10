pipeline {
    agent any
    stages {
        stage('prepare') {
            steps {
                git credentialsId: 'github', url: 'https://github.com/DmuMenDuo/campusqa'
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

        stage('docker') {
            steps {
                sh 'docker stop campusqa'
                sh 'docker rm -f campusqa'
                sh 'docker rmi $(docker images --filter=reference="campusqa" -q)'
                sh 'docker build -t campusqa .'
            }
        }

        stage('run') {
            steps{

                sh 'docker run --name=campusqa -itd -p 9997:9997 campusqa  --spring.profiles.active=test'
            }
        }

    }

}