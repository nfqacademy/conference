    module( "Controller test", {
      setup: function() {
        //prepare something for the suite
          controller = new Controller();
      }, teardown: function() {
        //do something after the finish
          controller = null;
      }
    });
    
    test( "test if  ", function() {
        ok(controller.validateForm("bla"), "formos validacija");
    });
    
    module( "Form tests", {
        setup: function() {
          //prepare something for the suite
            controller = new Controller();
        }, teardown: function() {
          //do something after the finish
            controller = null;
        }
      });
      
      test( "test if  ", function() {
          expect(2);
          equal(2, controller.getCount(), "count check");
          ok(controller.validateForm("bla"), "formos validacija");
          
      });
      
      test( "test if 2", 2, function() {
          expect(2);
          equal(2, controller.getCount(), "count check");
          ok(controller.validateForm("bla"), "formos validacija");
          
      });

      
      
  