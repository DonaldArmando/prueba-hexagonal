pipeline {
    agent {
        label 'Slave_Induccion'
    }

    options { 
      buildDiscarder(logRotator(numToKeepStr: '3'))
      disableConcurrentBuilds()
    }
    tools {
      jdk 'JDK8_Centos' 
      gradle 'Gradle5.0_Centos' 
    }
    stages{
        stage('Checkout') {
          steps{
        echo "------------>Checkout<------------"
        checkout([$class: 'GitSCM', branches: [[name: '*/master']],doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_DonaldArmando', url:'https://github.com/DonaldArmando/prueba-hexagonal.git']]])
        } 
    }

      stage('Unit Tests') {
        steps{
        echo "------------>Unit Tests<------------"
          sh 'gradle --b ./build.gradle test'
        }
      }


      stage('Static Code Analysis') {
        steps{
          echo '------------>Análisis de código estático<------------'
          withSonarQubeEnv('Sonar') {
            sh "${tool name: 'SonarScanner' , type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
          }
        }
      }

      stage('Build') {
        steps{
          echo "------------>Build<------------"
            sh 'gradle --b ./build.gradle build -x test'
      }
}






 }

 
  post {
    failure {
      echo 'This will run only if failed'
      mail (to: 'donald.torres@ceiba.com.co',subject: "FailedPipeline:${currentBuild.fullDisplayName}",body: "Something is wrongwith ${env.BUILD_URL}")
  }
}
 
 
}


