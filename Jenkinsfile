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
        // Removed DOCKER_USER and DOCKER_PASS from environment variables as they will be injected by withCredentials
        IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}" // This will be set inside withCredentials
        IMAGE_TAG = "${RELEASE}-${env.BUILD_NUMBER}"
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
                    // Correctly use Jenkins credentials to login to Docker Hub
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                        sh "docker login -u ${DOCKER_USER} --password-stdin <<< ${DOCKER_PASS}"
                        // Build the Docker image
                        def dockerImage = docker.build("${DOCKER_USER}/${APP_NAME}:${IMAGE_TAG}")
                        // Push the Docker image
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
}
