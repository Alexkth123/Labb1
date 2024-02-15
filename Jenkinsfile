pipeline {
    agent {
        label "node1"
    }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    environment{
        APP_NAME = "Labb1"
        RELEASE = "1.0.0"
        DOCKER_USER = "avarling"
        DOCKER_PASS = "alex123pass"
        IMAGE_NAME = "${DOCKER_USER}"+"/"+"${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
    }
    stages {
        stage("Clean up workspace") {
            steps {
                cleanWs()
            }
        }
        stage("Check out SCM") {
            steps {
                checkout scm: [
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        credentialsId: 'github',
                        url: 'https://github.com/Alexkth123/Labb1'
                    ]]
                ]
            }
        }
        stage("Build Application") {
            steps {
                sh "mvn clean package"
            }
        }

        stage("Test Application") {
            steps {
                sh "mvn test"
            }
        }

        stage("Build & Push Docker image ") {
            steps {
               script{
                docker.withRegistry('',DOCKER_PASS) {
                    docker_image = docker.Build "${IMAGE_NAME}"
                }

                docker.withRegistry('',DOCKER_PASS) {
                    docker_image.Push("${IMAGE_TAG}") 
                    docker_image.Push("latest") 
                }


               }
            }
        }

    }
}
