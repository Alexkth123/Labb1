pipeline {
    agent {
        label "node1"
    }
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }
    environment {
        APP_NAME = "labb1"
        RELEASE = "1.0.0"
        DOCKER_USER = "avarling"
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
                   // Use Jenkins credentials for Docker login
                    withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                        // Define IMAGE_NAME and IMAGE_TAG here to ensure they use correct, up-to-date values
                        def IMAGE_NAME = "${env.DOCKER_USER}/${env.APP_NAME}"
                        def IMAGE_TAG = "${env.RELEASE}-${env.BUILD_NUMBER}"
                        
                        echo "Building and pushing Docker image as ${IMAGE_NAME}:${IMAGE_TAG}"

                        // Build the Docker image
                        def dockerImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                        // Push the Docker image
                        dockerImage.push("${IMAGE_TAG}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
}
