import os
from time import sleep
import unittest

from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait # available since 2.4.0
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

time_out = 5

class WelcomeTests(unittest.TestCase):

    def setUp(self):
        desired_caps = {
            'platformName': 'android',
            'deviceName': 'Pixel 3 API 23',
            'appPackage': 'com.etsy.android',
            'appActivity': 'com.etsy.android.ui.homescreen.HomescreenTabsActivity'
        }
        self.driver = webdriver.Remote("http://0.0.0.0:4723/wd/hub", desired_caps)

    def tearDown(self):
        self.driver.quit()

    # transition events: fresh start -> sign in screen
    def transit_to_start(self):
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_link')))
        get_started = self.driver.find_element_by_id('com.etsy.android:id/btn_link')
        get_started.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_sign_in_dialog')))
        sign_in_jump = self.driver.find_element_by_id('com.etsy.android:id/btn_sign_in_dialog')
        sign_in_jump.click()

    # test: sign in empty
    def sign_in_empty(self):
        self.sign_in('', '')

    # test: sign in wrong
    def sign_in_wrong(self):
        self.sign_in('aaa', 'bbb')

    def sign_in(self, usrname_input, pwd_input):
        # input events: sign in by usrname_input and pwd_input
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/edit_username')))
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/edit_password')))
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_signin')))
        email_or_username = self.driver.find_element_by_id('com.etsy.android:id/edit_username')
        password = self.driver.find_element_by_id('com.etsy.android:id/edit_password')
        sign_in = self.driver.find_element_by_id('com.etsy.android:id/button_signin')
        email_or_username.send_keys(usrname_input)
        password.send_keys(pwd_input)
        
        # assert events: click sign in button
        sign_in.click()

    # test: forget password
    def forget_pwd(self):

        # assert events: click forget pwd button
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/forgot_password_link')))
        forget_pwd = self.driver.find_element_by_id('com.etsy.android:id/forgot_password_link')
        forget_pwd.click()

        # oracle: check if forget pwd dialog is displayed
        # self.assertTrue(self.driver.find_element_by_id('com.etsy.android:id/button_submit_password').is_displayed())

    # user session
    def test_welcome(self):
        current_dir = '%s/' % os.getcwd() + 'welcome.'

        self.transit_to_start()
        # test: sign in empty
        self.sign_in_empty()
        screenshot_name = 'sing_in_empty.png'
        self.driver.save_screenshot(current_dir + screenshot_name)
        # test: sign in wrong
        self.sign_in_wrong()
        screenshot_name = 'sign_in_wrong.png'
        self.driver.save_screenshot(current_dir + screenshot_name)
        # test: forget password
        self.forget_pwd()
        screenshot_name = 'forget_pwd.png'
        self.driver.save_screenshot(current_dir + screenshot_name)


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(WelcomeTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
