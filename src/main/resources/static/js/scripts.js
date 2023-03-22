window.addEventListener('DOMContentLoaded', event => {

    function cardClick(){
        console.log("click")
    }

    $('.blogCard').on('mouseover', function(e){
        const id = $(this).attr('id').replace("blogCard", "")
        const titleSelector = "#blogCardTitle"+id;
        $(titleSelector).css({'color': 'rgb(189,242,199)', 'font-style': 'italic'})
        $('.blogCard').css( 'cursor', 'grab' )
    })

    $('.blogCard').on('click', function(e){
        const id = $(this).attr('id').replace("blogCard", "")
        window.location.href = '/blog/post/retr/'+id;
    })

    $('.blogCard').on('mouseout', function(){
        $('.blogCardTitle').css({'color': 'white', 'font-style': 'normal'})
    })

    $('#refreshButton').on('click', function(){

        $('#refreshSpinner').prop({hidden:false})

        const authKey = $('#authKey').val()

        $.ajax({
            url: '/cache/videos',
            type: 'POST',
            data: authKey,
            success: function(data) {
                const message = data.message
                $('#refreshResponse').css({color: 'green'})
                $('#refreshResponse').text(message)
            },
            error: function(data) {
                const message = data.responseJSON.message
                $('#refreshResponse').css({color: 'red'})
                $('#refreshResponse').text(message)
            },
            complete: function(){
                $('#refreshSpinner').prop({hidden:true})
            }
        });

    })

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});

