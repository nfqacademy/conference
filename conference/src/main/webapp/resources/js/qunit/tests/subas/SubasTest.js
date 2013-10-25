    module( "subas test", {
      setup: function() {
        ok( true, "one extra assert per test" );
      }, teardown: function() {
        ok( true, "and one extra assert after each test" );
      }
    });
    test( "test with setup and teardown", function() {
      expect( 2 );
    });