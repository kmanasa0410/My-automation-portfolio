export class RegisterPage {
    constructor(page) {
        this.page = page;
        this.usernameInput = page.locator('#username');
        this.passwordInput = page.locator('#password');
        this.confirmPasswordInput = page.locator('#confirmPassword');
        this.registerButton = page.locator('button[type="submit"]');
        this.flashBanner = page.locator('#flash');
    }

    async navigate(){
        console.log('Navigating to user Registration page');
        await this.page.goto('https://practice.expandtesting.com/register');
        
    }

    async register(username, password){
        console.log(`filling registartion fields for generated user: ${username}`);
        await this.usernameInput.fill(username);
        await this.passwordInput.fill(password);
        await this.confirmPasswordInput.fill(password);
        await this.registerButton.click();
    }

    async getFlashMessageText(){
        return await this.flashBanner.textContent();
    }
}
