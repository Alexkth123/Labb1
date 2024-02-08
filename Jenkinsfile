pipeline{
    agent{
        label ""
    }
    tools{
        jdk 'Java17'
        maven 'Maven3'
    }
    stages{
        stage("Clean up workspace"){
            steps{
                cleanWs()
            }
        }
        stage("Check out SCM"){
            steps{
                git branch 'main' credentialsId: 'github', url: 'https://github.com/Alexkth123/Labb1'
            }
        }
        stage("Clean up workspace"){
                    steps{
                        cleanWs()
                    }
                }

    }



}