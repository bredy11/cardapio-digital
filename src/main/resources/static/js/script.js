document.addEventListener('DOMContentLoaded', function() {
    const menuItems = [
        { name: 'Meus Restaurantes', href: '#', visible: true },
        { name: 'Cardápios', href: '#', visible: true },
        { name: 'Pedidos', href: '#', visible: true },
        { name: 'Financeiro', href: '#', visible: false },
        { name: 'Analytics', href: '#', visible: true },
        { name: 'Configurações', href: '#', visible: true }
    ];

    const navItemsContainer = document.querySelector('.nav-items');

    function renderMenuItems() {
        navItemsContainer.innerHTML = ''; // Limpa os itens existentes
        const visibleItems = menuItems.filter(item => item.visible);

        visibleItems.forEach(item => {
            const listItem = document.createElement('li');
            const link = document.createElement('a');
            link.href = item.href;
            link.textContent = item.name;
            listItem.appendChild(link);
            navItemsContainer.appendChild(listItem);
        });
    }

    // Exemplo de como alterar a visibilidade (pode ser acionado por qualquer lógica de aplicativo)
    // Para este exemplo, vamos apenas renderizar o estado inicial.
    renderMenuItems();

    // Exemplo de função para atualizar o menu, que poderia ser chamada em diferentes telas
    window.updateMenuItems = function(newVisibility) {
        newVisibility.forEach(update => {
            const item = menuItems.find(i => i.name === update.name);
            if (item) {
                item.visible = update.visible;
            }
        });
        renderMenuItems();
    };
});