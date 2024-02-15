pipeline {
    agent {
        label "node1"
    }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    environment {
        APP_NAME = "Labb1"
        RELEASE = "1.0.0"
        // Assuming DOCKER_CREDENTIALS_ID is the ID of your Docker credentials stored in Jenkins
        DOCKER_CREDENTIALS_ID = "dockerhub"
        IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}"
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

        stage("Build & Push Docker Image") {
            steps {
                script {
                    // Login to Docker Hub securely
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin https://index.docker.io/v1/"
                    }
                    // Build the Docker image
                    def dockerImage = docker.build("$IMAGE_NAME:$IMAGE_TAG")
                    // Push the Docker image
                    dockerImage.push("$IMAGE_TAG")
                    dockerImage.push("latest")
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
