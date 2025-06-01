# FileViewer - Android Text File Handling Application

[![Android CI](https://github.com/yourusername/FileViewer/actions/workflows/android-ci.yml/badge.svg)](https://github.com/yourusername/FileViewer/actions/workflows/android-ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

FileViewer is a lightweight Android application demonstrating core file handling operations on internal storage. Built with Java in Android Studio, this app provides a clean interface for creating, writing, and reading text files directly on your device.

## Features

- **File Creation**: Generate new text files in device internal storage
- **Text Editing**: Write and modify file content with keyboard support
- **File Reading**: View saved files with clean text display
- **Storage Management**: Automatic path generation and file organization
- **Data Persistence**: Files remain accessible between app sessions
- **Confirmation Dialogs**: Save/discard prompts to prevent data loss

## Screenshots

| Main Interface | File Editing | Storage Info |
|----------------|-------------|--------------|
| <img src="screenshots/Screenshot_2025-06-01_131746.png" width="300"> | <img src="screenshots/Screenshot_2025-06-01_131808.png" width="300"> | <img src="screenshots/Screenshot_2025-06-01_132913.png" width="300"> |

## Technical Implementation

```mermaid
graph TD
    A[Main Activity] --> B[Create File]
    A --> C[Write File]
    A --> D[Read File]
    B --> E[FileOutputStream]
    C --> F[EditText + Save Handler]
    D --> G[TextView Display]
    E --> H[Internal Storage]
    F --> H
    G --> H

## Installation

Follow these steps to set up and run the FileViewer project:

### Prerequisites
- [Android Studio Giraffe (2022.3.1) or later](https://developer.android.com/studio)
- Android SDK 34 (Android 14)
- Java Development Kit (JDK) 17
- Android device/emulator with API 34+

### Step-by-Step Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/FileViewer.git
   cd FileViewer
