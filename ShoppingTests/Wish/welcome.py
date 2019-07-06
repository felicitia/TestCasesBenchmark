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
            'appPackage': 'com.contextlogic.wish',
            'appActivity': 'com.contextlogic.wish.activity.browse.BrowseActivity'
        }
        self.driver = webdriver.Remote("http://0.0.0.0:4723/wd/hub", desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_sign_in_wrong(self):
        # wait for elements
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_email_text')))
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_password_text')))
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_sign_in_button')))
        # find elements
        email = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_email_text')
        password = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_password_text')
        sign_in = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_sign_in_button')
        # testing...
        email.send_keys('aaa')
        password.send_keys('bbb')
        sign_in.click()
        # oracle
        self.assertTrue(self.driver.find_element_by_id('com.contextlogic.wish:id/multi_button_dialog_fragment_content').is_displayed())

    def test_sign_in_empty(self):
        # wait for elements
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_sign_in_button')))
        # find elements
        sign_in = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_sign_in_button')
        # testing...
        sign_in.click()
        # oracle
        self.assertTrue(self.driver.find_element_by_id('com.contextlogic.wish:id/multi_button_dialog_fragment_content').is_displayed())

    def test_forget_pwd(self):
        # wait for elements
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_forgot_password_button')))
        # find elements
        forget_pwd = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_forgot_password_button')
        # testing...
        forget_pwd.click()
        # oracle
        self.assertTrue(self.driver.find_element_by_id('com.contextlogic.wish:id/forgot_password_fragment_reset_password_button').is_displayed())

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(WelcomeTests)
    unittest.TextTestRunner(verbosity=2).run(suite)