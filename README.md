# 🎲 Multiplayer Dice Game Simulator

Welcome to the **Multiplayer Dice Game Simulator**! This is a beautifully styled, premium local multiplayer game built with Java Swing. It supports up to 4 players, rolls interactive virtual dice, showcases detailed match results, tracks an automated scoreboard, and features a local logging framework.

---

## ✨ Features

* **🎨 Sleek, Modern UI**: Designed with custom HSL-tailored gradients, subtle rounded shapes, modern typography, and glassmorphism-inspired components.
* **👥 Pass-and-Play Local Multiplayer**: Set up between 1 to 4 players and input customized names directly using interactive dialog boxes.
* **📈 Leaderboard & Stats**: Tracks player win histories across rounds and dynamically lists overall rankings.
* **📝 Automated File Logging**: Automatically records round-by-round statistics and results directly to a local `results.txt` file.
* **🔌 Standalone Game Server**: Features an optional standalone socket server (`GameServer`) listening on port `9999` to intercept and print gameplay events.

---

## 📂 Project Architecture

```bash
MultiplayerDice Game/
├── Dice Game Simulator/
│   ├── Dice.java          # Core dice engine (generates random 1-6 values)
│   ├── Player.java        # Entity for name tracking and round wins
│   ├── GameEngine.java    # Manages rounds, calculates winners, and logs to files
│   ├── Executer.java      # Custom Swing GUI, button events, and gameplay loop
│   └── GameServer.java    # Standalone socket server for intercepting log events
├── .gitignore             # Standard git exclusions (ignores build folders and local logs)
└── README.md              # Project documentation (You are here!)
```

---

## 🚀 How to Run

Make sure you have Java installed (`version 8` or above).

### 1. Launch the Game Simulator

Open your terminal or command prompt and run the following commands:

```bash
# Navigate to the simulator directory
cd "Dice Game Simulator"

# Run the pre-compiled game (or compile using `javac *.java` first)
java Executer
```

---

### 2. Launch the Standalone Server (Optional)

If you'd like to listen to socket logging events:

1. Open a separate terminal window and start the socket server:
   ```bash
   cd "Dice Game Simulator"
   java GameServer
   ```
2. Launch the game in another window:
   ```bash
   java Executer
   ```

*The server will print connected clients and real-time roll updates directly inside its console log!*

---

## 🛠️ Build and Compilation

If you make modifications to the source code and want to recompile the project:

```bash
cd "Dice Game Simulator"
javac *.java
```

---

## 📦 GitHub Upload Readiness

We've already equipped this repository with a root-level `.gitignore` file. It automatically excludes:
* Compiled `.class` binaries
* IDE configurations (`.idea/` and `.iml`)
* Local logs (`results.txt`)

This ensures that your GitHub repository remains perfectly clean and professional.
