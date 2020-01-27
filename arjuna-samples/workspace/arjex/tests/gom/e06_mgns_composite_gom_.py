import unittest

from cgom.wordpress import WordPress

class WPBaseTest(unittest.TestCase):

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.__app = None
        self.dashboard_page = None

    @property
    def app(self):
        return self.__app

    def setUp(self):
        self.__app = WordPress(gns_format="mgns")
        home = self.app.launch()
        self.dashboard_page = home.login_with_default_creds()

    def tearDown(self):
        self.dashboard_page.top_nav.logout()
        # self.automator.quit()

class CompositeGOMTest(WPBaseTest):

    def test_author_type_selection(self):
        self.dashboard_page.left_nav.settings.tweak_settings()

unittest.main()