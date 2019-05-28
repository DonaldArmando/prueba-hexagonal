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




 }

 

 
 
}