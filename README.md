# TavoosSDK

TavoosSDK is a library that provides functionality for retrieving a VAST address using device information and advertising ID. It can be used to integrate pre-roll ads into your Android applications.

## Features

- Retrieves VAST address with device information and advertising ID.
- Supports custom secret key for secure communication.

## Installation

To use TavoosSDK in your Android project, add the following dependency to your app's `build.gradle` file:

```gradle
implementation 'com.github.amirb938:TavoosSDK:<latest_version>'
```

## Usage

1. Import the `TavoosSDK` class from the `ir.fastclick.core` package:

   ```kotlin
   import ir.fastclick.core.TavoosSDK
   ```

2. Create an instance of the TavoosSDK class and set a secret key:

   ```kotlin
   val tavoosSDK = TavoosSDK().apply {
       setSecretKey("your-secret-key")
   }
   ```

3. Use the getVastAddress method to retrieve the VAST address:
   ```kotlin
   CoroutineScope(Dispatchers.Main).launch {
       val vastAddress = withContext(Dispatchers.IO) {
           tavoosSDK.getVastAddress(this@MainActivity)
       }
       // Use the obtained VAST address as needed
   }
   ```

Note: Make sure you have the appropriate permissions and configurations in place for using the Advertising ID.
## Example
For a complete example of how to use TavoosSDK in an Android application, refer to the provided sample MainActivity code in the ir.fastclick.tavoossdk package.


