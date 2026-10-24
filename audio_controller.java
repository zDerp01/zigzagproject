import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * audio_controller - gere as músicas e efeitos sonoros do jogo.
 */
public class audio_controller extends Controllers
{
    private GreenfootSound _stepSfx, _jumpSfx, _menuMusic;
    private boolean _isMenu;
    
    /**
     * GetStepSfx - retorna o som de passos.
     */
    public GreenfootSound GetStepSfx() {
        return _stepSfx;
    }
    
    /**
     * GetMenuMusic - retorna o som da música do menu.
     */
    public GreenfootSound GetMenuMusic() {
        return _menuMusic;
    }
    
    /**
     * GetJumpSfx - retorna o som do salto.
     */
    public GreenfootSound GetJumpSfx() {
        return _jumpSfx;
    }
    
    /**
     * audio_controller - construtor que atribui o respetivo som ao respetivo atributo.
     */
    public audio_controller(boolean isMenu) {
        _stepSfx = new GreenfootSound("step.mp3");
        _jumpSfx = new GreenfootSound("jump.mp3");
        _menuMusic = new GreenfootSound("menuMusic.mp3");
        
        _isMenu = isMenu;
    }
    
    /**
     * Act - do whatever the zag wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (_isMenu && !_menuMusic.isPlaying()) PlayMusic();
    }

    /**
     * PlayMusic - inicia a música de fundo em loop.
     */
    public void PlayMusic() {
        StopMusic();
        _menuMusic.playLoop();
    }
    
    /**
     * PlayMusic - para a música de fundo.
     */
    public void StopMusic() {
        _menuMusic.stop();
    }

    /**
     * PlaySFX - toca um efeito sonoro.
     */
    public void PlaySFX(GreenfootSound sound) {
        sound.play();
    }
}