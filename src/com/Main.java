package com;

import com.application.Application;
import com.platform.Platform;

public class Main {

    public static void main(String[] args) {

        Platform platform = new Platform(1, 1);
        Application app = new Application(7, 6);

        platform.runApplication(app);
    }
}

//  Task Matrix
// 3.0 7.5 0.28 0.028
// 10.0 23.1 0.45 0.045
// 6.0 13.5 0.30 0.03
// 6.5 14.6 0.40 0.04
// 5.0 9.4 0.25 0.025
// 4.5 7.9 0.20 0.02
// 4.0 7.0 0.20 0.02

//  Dependencies
// 0 1
// 0 2
// 0 4
// 1 3
// 1 5
// 2 6
