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

    def test_sign_in_wrong(self):
        # user inputs
        usrname_input = 'aaa'
        pwd_input = 'bbb'

        # transition events: go to sign in screen
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_link')))
        get_started = self.driver.find_element_by_id('com.etsy.android:id/btn_link')
        get_started.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_sign_in_dialog')))
        sign_in_jump = self.driver.find_element_by_id('com.etsy.android:id/btn_sign_in_dialog')
        sign_in_jump.click()

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
        # oracle: check if wrong sign in message is displayed
        self.assertTrue(self.driver.find_element_by_id('com.etsy.android:id/txt_error').is_displayed())

    def test_sign_in_empty(self):
        # transition events: go to sign in screen
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_link')))
        get_started = self.driver.find_element_by_id('com.etsy.android:id/btn_link')
        get_started.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_sign_in_dialog')))
        sign_in_jump = self.driver.find_element_by_id('com.etsy.android:id/btn_sign_in_dialog')
        sign_in_jump.click()

        # assert events: click sign in button
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_signin')))
        sign_in = self.driver.find_element_by_id('com.etsy.android:id/button_signin')
        sign_in.click()
        # oracle: check empty message

    def test_forget_pwd(self):
        # transition events: go to sign in screen
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_link')))
        get_started = self.driver.find_element_by_id('com.etsy.android:id/btn_link')
        get_started.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_sign_in_dialog')))
        sign_in_jump = self.driver.find_element_by_id('com.etsy.android:id/btn_sign_in_dialog')
        sign_in_jump.click()

        # assert events: click forget pwd button
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/forgot_password_link')))
        forget_pwd = self.driver.find_element_by_id('com.etsy.android:id/forgot_password_link')
        forget_pwd.click()

        # oracle: check if forget pwd dialog is displayed
        self.assertTrue(self.driver.find_element_by_id('com.etsy.android:id/button_submit_password').is_displayed())

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(WelcomeTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
