// IWebviewProcessToMainProcessInterface.aidl
package com.v.webview_module;

// Declare any non-default types here with import statements
// webview process to main process interface
interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName,String jsonParams);
}