# C323 Project 3 - Math Practice App

This is a simple math practice application designed for Android devices. 

## Features

### Core Functionality
The following key features are included in the app:

- [x] **Customizable Practice Options**: Users can select the type of math problem (addition, subtraction, multiplication, or division), the difficulty level (easy, medium, hard), and the number of questions they wish to answer.
- [x] **Question Tracking**: The app delivers a sequence of math problems based on the user's preferences, keeping track of the questions and user answers.
- [x] **Results Summary**: Upon completion, the app provides feedback with the total number of correct and incorrect answers.
- [x] **Sound and Visual Feedback**: Plays a sound and displays a toast message based on the user's answers (correct or incorrect).

## Demo

Here's a video walkthrough :

![Video Walkthrough](walkthrough.gif)

## Challenges & Learnings

During development, I encountered some issues while setting up safe args for navigation between fragments. However, 
I managed to resolve them after exploring the correct configuration and updating the necessary dependencies.

Additionally, handling the feedback logic for sounds and toast messages required attention to avoid memory leaks and crashes.

## License

© 2024 Weidi Zheng

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is provided on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
